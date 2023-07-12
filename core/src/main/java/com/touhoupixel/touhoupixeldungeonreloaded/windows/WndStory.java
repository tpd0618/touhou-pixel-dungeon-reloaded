package com.touhoupixel.touhoupixeldungeonreloaded.windows;

import com.touhoupixel.touhoupixeldungeonreloaded.Chrome;
import com.touhoupixel.touhoupixeldungeonreloaded.scenes.PixelScene;
import com.touhoupixel.touhoupixeldungeonreloaded.ui.RenderedTextBlock;
import com.touhoupixel.touhoupixeldungeonreloaded.ui.Window;
import com.watabou.input.PointerEvent;
import com.watabou.noosa.Game;
import com.watabou.noosa.Image;
import com.watabou.noosa.PointerArea;

public class WndStory extends Window {

    private static final int WIDTH_P = 125;
    private static final int WIDTH_L = 180;
    private static final int MARGIN = 2;

    private IconTitle ttl;
    private RenderedTextBlock tf;

    private float appearDelay;
    private float disappearDelay;

    public WndStory( String text ) {
        this( null, null, text );
    }

    public WndStory(Image icon, String title, String text ) {
        super( 0, 0, Chrome.get( Chrome.Type.SCROLL ) );

        int width = PixelScene.landscape() ? WIDTH_L - MARGIN * 2: WIDTH_P - MARGIN *2;

        float y = MARGIN;
        if (icon != null && title != null){
            ttl = new IconTitle(icon, title);
            ttl.setRect(MARGIN, y, width-2*MARGIN, 0);
            y = ttl.bottom()+MARGIN;
            add(ttl);
            ttl.tfLabel.invert();
        }

        tf = PixelScene.renderTextBlock( text, 6 );
        tf.maxWidth(width);
        tf.invert();
        tf.setPos(MARGIN, y);
        add( tf );

        PointerArea blocker = new PointerArea( 0, 0, PixelScene.uiCamera.width, PixelScene.uiCamera.height ) {
            @Override
            protected void onClick( PointerEvent event ) {
                onBackPressed();
            }
        };
        blocker.camera = PixelScene.uiCamera;
        add(blocker);

        resize( width + 2*MARGIN, (int)(tf.bottom()+MARGIN) );
    }

    public WndStory setDelays(float appearDelay, float disappearDelay){
        this.appearDelay = appearDelay;
        if (appearDelay > 0){
            shadow.visible = chrome.visible = tf.visible = false;
            if (ttl != null) ttl.visible = false;
        }

        this.disappearDelay = disappearDelay;
        return this;
    }

    @Override
    public void onBackPressed() {
        if (appearDelay <= 0 && disappearDelay <= 0) {
            super.onBackPressed();
        }
    }

    @Override
    public void update() {
        super.update();

        if (appearDelay > 0) {
            appearDelay -= Game.elapsed;
            if (appearDelay <= 0) {
                shadow.visible = chrome.visible = tf.visible = true;
                if (ttl != null) ttl.visible = true;
            }
        } else if (disappearDelay > 0){
            disappearDelay -= Game.elapsed;
        }
    }
}