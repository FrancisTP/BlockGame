package com.tp.batman.francis.blockgame.game.Assets;

import com.tp.batman.francis.blockgame.framework.gl.SpriteBatcher;
import com.tp.batman.francis.blockgame.framework.gl.TextureRegion;

/**
 * Created by Francis on 2017-04-26.
 */
public class Character {

    private float x, y;
    private float width, height;
    private float size;
    private String colour;
    private char character;
    private TextureRegion sprite;

    public Character(char character, int size, String colour) {
        this.character = character;
        this.size = size;
        this.colour = colour;

        if (this.character == 'a') {
            sprite = Assets.textAssets.a_lower;
        } else if (this.character == 'b') {
            sprite = Assets.textAssets.b_lower;
        } else if (this.character == 'c') {
            sprite = Assets.textAssets.c_lower;
        } else if (this.character == 'd') {
            sprite = Assets.textAssets.d_lower;
        } else if (this.character == 'e') {
            sprite = Assets.textAssets.e_lower;
        } else if (this.character == 'f') {
            sprite = Assets.textAssets.f_lower;
        } else if (this.character == 'g') {
            sprite = Assets.textAssets.g_lower;
        } else if (this.character == 'h') {
            sprite = Assets.textAssets.h_lower;
        } else if (this.character == 'i') {
            sprite = Assets.textAssets.i_lower;
        } else if (this.character == 'j') {
            sprite = Assets.textAssets.j_lower;
        } else if (this.character == 'k') {
            sprite = Assets.textAssets.k_lower;
        } else if (this.character == 'l') {
            sprite = Assets.textAssets.l_lower;
        } else if (this.character == 'm') {
            sprite = Assets.textAssets.m_lower;
        } else if (this.character == 'n') {
            sprite = Assets.textAssets.n_lower;
        } else if (this.character == 'o') {
            sprite = Assets.textAssets.o_lower;
        } else if (this.character == 'p') {
            sprite = Assets.textAssets.p_lower;
        } else if (this.character == 'q') {
            sprite = Assets.textAssets.q_lower;
        } else if (this.character == 'r') {
            sprite = Assets.textAssets.r_lower;
        } else if (this.character == 's') {
            sprite = Assets.textAssets.s_lower;
        } else if (this.character == 't') {
            sprite = Assets.textAssets.t_lower;
        } else if (this.character == 'u') {
            sprite = Assets.textAssets.u_lower;
        } else if (this.character == 'v') {
            sprite = Assets.textAssets.v_lower;
        } else if (this.character == 'w') {
            sprite = Assets.textAssets.w_lower;
        } else if (this.character == 'x') {
            sprite = Assets.textAssets.x_lower;
        } else if (this.character == 'y') {
            sprite = Assets.textAssets.y_lower;
        } else if (this.character == 'z') {
            sprite = Assets.textAssets.z_lower;
        } else if (this.character == 'A') {
            sprite = Assets.textAssets.a_upper;
        } else if (this.character == 'B') {
            sprite = Assets.textAssets.b_upper;
        } else if (this.character == 'C') {
            sprite = Assets.textAssets.c_upper;
        } else if (this.character == 'D') {
            sprite = Assets.textAssets.d_upper;
        } else if (this.character == 'E') {
            sprite = Assets.textAssets.e_upper;
        } else if (this.character == 'F') {
            sprite = Assets.textAssets.f_upper;
        } else if (this.character == 'G') {
            sprite = Assets.textAssets.g_upper;
        } else if (this.character == 'H') {
            sprite = Assets.textAssets.h_upper;
        } else if (this.character == 'I') {
            sprite = Assets.textAssets.i_upper;
        } else if (this.character == 'J') {
            sprite = Assets.textAssets.j_upper;
        } else if (this.character == 'K') {
            sprite = Assets.textAssets.k_upper;
        } else if (this.character == 'L') {
            sprite = Assets.textAssets.l_upper;
        } else if (this.character == 'M') {
            sprite = Assets.textAssets.m_upper;
        } else if (this.character == 'N') {
            sprite = Assets.textAssets.n_upper;
        } else if (this.character == 'O') {
            sprite = Assets.textAssets.o_upper;
        } else if (this.character == 'P') {
            sprite = Assets.textAssets.p_upper;
        } else if (this.character == 'Q') {
            sprite = Assets.textAssets.q_upper;
        } else if (this.character == 'R') {
            sprite = Assets.textAssets.r_upper;
        } else if (this.character == 'S') {
            sprite = Assets.textAssets.s_upper;
        } else if (this.character == 'T') {
            sprite = Assets.textAssets.t_upper;
        } else if (this.character == 'U') {
            sprite = Assets.textAssets.u_upper;
        } else if (this.character == 'V') {
            sprite = Assets.textAssets.v_upper;
        } else if (this.character == 'W') {
            sprite = Assets.textAssets.w_upper;
        } else if (this.character == 'X') {
            sprite = Assets.textAssets.x_upper;
        } else if (this.character == 'Y') {
            sprite = Assets.textAssets.y_upper;
        } else if (this.character == 'Z') {
            sprite = Assets.textAssets.z_upper;
        } else if (this.character == '0') {
            sprite = Assets.textAssets.number_0;
        } else if (this.character == '1') {
            sprite = Assets.textAssets.number_1;
        } else if (this.character == '2') {
            sprite = Assets.textAssets.number_2;
        } else if (this.character == '3') {
            sprite = Assets.textAssets.number_3;
        } else if (this.character == '4') {
            sprite = Assets.textAssets.number_4;
        } else if (this.character == '5') {
            sprite = Assets.textAssets.number_5;
        } else if (this.character == '6') {
            sprite = Assets.textAssets.number_6;
        } else if (this.character == '7') {
            sprite = Assets.textAssets.number_7;
        } else if (this.character == '8') {
            sprite = Assets.textAssets.number_8;
        } else if (this.character == '9') {
            sprite = Assets.textAssets.number_9;
        } else if (this.character == '.') {
            sprite = Assets.textAssets.period;
        } else if (this.character == '?') {
            sprite = Assets.textAssets.question_mark;
        } else if (this.character == '!') {
            sprite = Assets.textAssets.exclamation_mark;
        } else if (this.character == ',') {
            sprite = Assets.textAssets.comma;
        } else if (this.character == '-') {
            sprite = Assets.textAssets.hyphen;
        } else if (this.character == '#') {
            sprite = Assets.textAssets.pound;
        } else if (this.character == '$') {
            sprite = Assets.textAssets.dollar;
        } else if (this.character == '=') {
            sprite = Assets.textAssets.equal;
        } else if (this.character == ':') {
            sprite = Assets.textAssets.semi_colon;
        } else if (this.character == '\'') {
            sprite = Assets.textAssets.apostrophe;
        } else if (this.character == '"') {
            sprite = Assets.textAssets.quotation_mark;
        } else if (this.character == '(') {
            sprite = Assets.textAssets.parenthesis_left;
        } else if (this.character == ')') {
            sprite = Assets.textAssets.parenthesis_right;
        }  else if (this.character == '@') {
            sprite = Assets.textAssets.at;
        } else if (this.character == '/') {
            sprite = Assets.textAssets.forward_dash;
        } else if (this.character == '\\') {
            sprite = Assets.textAssets.backwards_dash;
        } else if (this.character == ' ') {
            sprite = null;
        } else {
            sprite = Assets.textAssets.question_mark;
        }

        if (sprite != null) {
            width = sprite.width * (this.size / 10);
            height = sprite.height * (this.size / 10);
        } else {
            width = 20 * (this.size / 10);
            height = 25 * (this.size / 10);
        }
    }

    public void addY(float amount) {
        y += amount;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y, float biggestHeight) {
        if (this.character == ',') {
            this.y = y - (height/2);
        } else if (this.character == '-') {
            this.y = y + (biggestHeight/2) - (height/2);
        } else if (this.character == '\'' || this.character == '"') {
            this.y = y + biggestHeight - (height);//(height/2)
        } else if (this.character == 'g' || this.character == 'j' || this.character == 'p' || this.character == 'q' || this.character == 'y') {
            this.y = y - (height/3) + (height/9);
        } else if (this.character == '0' || this.character == '6') {
            this.y = y - (height/50);
        } else {
            this.y = y;
        }
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public char getCharacter() {
        return character;
    }

    public void render(SpriteBatcher batcher) {
        if (sprite != null) {
            batcher.drawSprite(x + width/2, y + height/2, width, height, sprite);
        }
    }
}
