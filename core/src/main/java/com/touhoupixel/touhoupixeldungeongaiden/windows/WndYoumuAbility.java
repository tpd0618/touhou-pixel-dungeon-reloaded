package com.touhoupixel.touhoupixeldungeongaiden.windows;


import com.touhoupixel.touhoupixeldungeongaiden.scenes.PixelScene;
import com.touhoupixel.touhoupixeldungeongaiden.ui.RedButton;
import com.touhoupixel.touhoupixeldungeongaiden.ui.RenderedTextBlock;
import com.touhoupixel.touhoupixeldungeongaiden.ui.Window;

public class WndYoumuAbility extends Window {
    private static final int WIDTH_P = 120;
    private static final int WIDTH_L = 160;

    private static final int MARGIN  = 2;
    private static final int BUTTON_HEIGHT	= 18;


    public WndYoumuAbility( String title, String message, String... options ) {
        super();

        int width = PixelScene.landscape() ? WIDTH_L : WIDTH_P;

        float pos = MARGIN;
            RenderedTextBlock tfTitle = PixelScene.renderTextBlock(title, 9);
            tfTitle.hardlight(TITLE_COLOR);
            tfTitle.setPos(MARGIN, pos);
            tfTitle.maxWidth(width - MARGIN * 2);
            add(tfTitle);

            pos = tfTitle.bottom() + 3*MARGIN;

        layoutBody(pos, message, options);
    }

    private void layoutBody(float pos, String message, String... options){
        int width = PixelScene.landscape() ? WIDTH_L : WIDTH_P;

        RenderedTextBlock tfMesage = PixelScene.renderTextBlock( 6 );
        tfMesage.text(message, width);
        tfMesage.setPos( 0, pos );
        add( tfMesage );

        pos = tfMesage.bottom() + 2*MARGIN;

        for (int i=0; i < options.length; i++) {
            final int index = i;
            RedButton btn = new RedButton( options[i], 6 ) {
                @Override
                protected void onClick() {
                    hide();
                    onSelect( index );
                }
            };
            btn.enable(enabled(i));


            btn.leftJustify = true;
            btn.multiline = true;
            btn.setSize(width, btn.reqHeight());
            btn.setRect(0, pos, width, btn.reqHeight());
            add( btn );
            pos = btn.bottom() + MARGIN;
        }


        resize( width, (int)(pos - MARGIN) );
    }

    protected boolean enabled( int index ){
        return true;
    }

    protected void onSelect( int index ) {}


}
