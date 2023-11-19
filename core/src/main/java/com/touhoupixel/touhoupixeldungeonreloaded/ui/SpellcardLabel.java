package com.touhoupixel.touhoupixeldungeonreloaded.ui;

import com.touhoupixel.touhoupixeldungeonreloaded.Chrome;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.Actor;
import com.touhoupixel.touhoupixeldungeonreloaded.actors.spellcards.Spellcard;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.PixelScene;
import com.touhoupixel.touhoupixeldungeonreloaded.utils.GLog;
import com.watabou.noosa.BitmapText;
import com.watabou.noosa.Game;
import com.watabou.noosa.NinePatch;
import com.watabou.noosa.ui.Component;
import com.watabou.utils.Random;
public class SpellcardLabel extends Component {
    private float r;
    private float g;
    private float b;
    protected float lightness = 0;
    private BitmapText spellcardName;
    protected NinePatch bg;
    private Spellcard lastSpell = null;
    public static float SIZE = 70;
    public static float HEIGHT = 14;
    public SpellcardLabel(int color){
        super();

        this.r = (color >> 16) / 255f;
        this.g = ((color >> 8) & 0xFF) / 255f;
        this.b = (color & 0xFF) / 255f;
    }
    @Override
    protected void createChildren() {
        super.createChildren();

        bg = Chrome.get( Chrome.Type.TAG );
        add( bg );

        spellcardName = new BitmapText(PixelScene.pixelFont);
        add(spellcardName);

        visible = true;
    }
    protected void layout() {
        super.layout();

        bg.size(SIZE, HEIGHT);
        bg.x = x;
        bg.y = y;

        spellcardName.x = x;
        spellcardName.y = y;
        PixelScene.align(spellcardName);
        visible = false;
    }
    @Override
    public void update(){

        if (!Spellcard.isThereActiveCards) {
            visible = false;
            return;
        }
        Spellcard oldSpell = lastSpell;
        lastSpell = null;
            for (Actor a : Actor.all()){
                if (a instanceof Spellcard){
                    lastSpell = (Spellcard) a;
                }
            }
            if (lastSpell == null){
                visible = false;
                return;
            } else if (oldSpell == null) {
                changeText();
            } else if (!lastSpell.equals(oldSpell) || lastSpell.hashCode() != oldSpell.hashCode()) {
                changeText();
            }


        if (visible && lightness > 0.5) {
            if ((lightness -= Game.elapsed) > 0.5) {
                bg.ra = bg.ga = bg.ba = 2 * lightness - 1;
                bg.rm = 2 * r * (1 - lightness);
                bg.gm = 2 * g * (1 - lightness);
                bg.bm = 2 * b * (1 - lightness);
            } else {
                bg.hardlight( r, g, b );
            }
        }
    }
    public void flash() {
        lightness = 1f;
    }
    public void changeText(){
        spellcardName.text(lastSpell.name());
        spellcardName.measure();
        spellcardName.y = y + spellcardName.height / 2;

        spellcardName.x = x - (spellcardName.width() + 2);
        bg.x = spellcardName.x - 4;
        visible = true;
        flash();
    }

}
