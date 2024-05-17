package com.mygdx.game.billiards;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import static com.mygdx.game.billiards.MyBilliards.SCR_WIDTH;

public class MyButton {
    float x, y;
    float width, height;
    BitmapFont font;
    String text;
    private boolean isTextButton;

    public MyButton(float x, float y, float size) {
        this.x = x;
        this. y = y;
        width=height=size;
    }

    public MyButton(float x, float y, float width, float height) {
        this.x=x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    public MyButton(String text, BitmapFont font, float x, float y){
        this.text = text;
        this.font = font;
        this.x = x;
        this.y = y;
        GlyphLayout glyphLayout = new GlyphLayout(font, text);
        width = glyphLayout.width;
        height = glyphLayout.height;
        isTextButton = true;
    }
    public MyButton(String text, BitmapFont font, float y){
        this.text = text;
        this.font = font;
        this.y = y;
        GlyphLayout glyphLayout = new GlyphLayout(font, text);
        width = glyphLayout.width;
        height = glyphLayout.height;
        x=SCR_WIDTH/2-width/2;
        isTextButton = true;
    }
    boolean hit(float touchX, float touchY) {
        if (isTextButton) {
            return x < touchX & touchY < x + width & y > touchY & touchY > y - height;
        }
        return  x<touchX & touchX<x+width & y<touchY & touchY<y+height;
    }
}

