package com.tp.batman.francis.blockgame.game.Assets.Seperate;

import com.tp.batman.francis.blockgame.framework.gl.Texture;
import com.tp.batman.francis.blockgame.framework.gl.TextureRegion;
import com.tp.batman.francis.blockgame.framework.impl.GLGame;

public class TextAssets {

    protected static GLGame glGame;


    // TextOld
    public static Texture textSpriteSheet;

    public static TextureRegion a_lower;
    public static TextureRegion a_upper;
    public static TextureRegion apostrophe;
    public static TextureRegion at;
    public static TextureRegion b_lower;
    public static TextureRegion b_upper;
    public static TextureRegion backwards_dash;
    public static TextureRegion c_lower;
    public static TextureRegion c_upper;
    public static TextureRegion comma;
    public static TextureRegion d_lower;
    public static TextureRegion d_upper;
    public static TextureRegion dollar;
    public static TextureRegion e_lower;
    public static TextureRegion e_upper;
    public static TextureRegion equal;
    public static TextureRegion exclamation_mark;
    public static TextureRegion f_lower;
    public static TextureRegion f_upper;
    public static TextureRegion forward_dash;
    public static TextureRegion g_lower;
    public static TextureRegion g_upper;
    public static TextureRegion gear_01;
    public static TextureRegion gear_02;
    public static TextureRegion h_lower;
    public static TextureRegion h_upper;
    public static TextureRegion hyphen;
    public static TextureRegion i_lower;
    public static TextureRegion i_upper;
    public static TextureRegion j_lower;
    public static TextureRegion j_upper;
    public static TextureRegion k_lower;
    public static TextureRegion k_upper;
    public static TextureRegion l_lower;
    public static TextureRegion l_upper;
    public static TextureRegion m_lower;
    public static TextureRegion m_upper;
    public static TextureRegion n_lower;
    public static TextureRegion n_upper;
    public static TextureRegion number_0;
    public static TextureRegion number_1;
    public static TextureRegion number_2;
    public static TextureRegion number_3;
    public static TextureRegion number_4;
    public static TextureRegion number_5;
    public static TextureRegion number_6;
    public static TextureRegion number_7;
    public static TextureRegion number_8;
    public static TextureRegion number_9;
    public static TextureRegion o_lower;
    public static TextureRegion o_upper;
    public static TextureRegion p_lower;
    public static TextureRegion p_upper;
    public static TextureRegion parenthesis_left;
    public static TextureRegion parenthesis_right;
    public static TextureRegion period;
    public static TextureRegion pound;
    public static TextureRegion q_lower;
    public static TextureRegion q_upper;
    public static TextureRegion question_mark;
    public static TextureRegion quotation_mark;
    public static TextureRegion r_lower;
    public static TextureRegion r_upper;
    public static TextureRegion s_lower;
    public static TextureRegion s_upper;
    public static TextureRegion semi_colon;
    public static TextureRegion t_lower;
    public static TextureRegion t_upper;
    public static TextureRegion text_background;
    public static TextureRegion twitter_board_bottom;
    public static TextureRegion twitter_board_top;
    public static TextureRegion u_lower;
    public static TextureRegion u_upper;
    public static TextureRegion v_lower;
    public static TextureRegion v_upper;
    public static TextureRegion w_lower;
    public static TextureRegion w_upper;
    public static TextureRegion x_lower;
    public static TextureRegion x_upper;
    public static TextureRegion y_lower;
    public static TextureRegion y_upper;
    public static TextureRegion z_lower;
    public static TextureRegion z_upper;

    public static void load(GLGame game){
        glGame = game;
        load();
    }


    public static void load(){
        textSpriteSheet = new Texture(glGame, "other/text.png");

        a_lower = new TextureRegion(textSpriteSheet, 606, 144, 32, 31);
        a_upper = new TextureRegion(textSpriteSheet, 88, 146, 32, 43);
        apostrophe = new TextureRegion(textSpriteSheet, 649, 214, 7, 12);
        at = new TextureRegion(textSpriteSheet, 512, 3, 38, 43);
        b_lower = new TextureRegion(textSpriteSheet, 463, 88, 32, 43);
        b_upper = new TextureRegion(textSpriteSheet, 554, 3, 32, 43);
        backwards_dash = new TextureRegion(textSpriteSheet, 713, 179, 20, 40);
        c_lower = new TextureRegion(textSpriteSheet, 642, 144, 32, 31);
        c_upper = new TextureRegion(textSpriteSheet, 124, 146, 32, 43);
        comma = new TextureRegion(textSpriteSheet, 638, 214, 7, 19);
        d_lower = new TextureRegion(textSpriteSheet, 590, 3, 32, 43);
        d_upper = new TextureRegion(textSpriteSheet, 160, 146, 32, 43);
        dollar = new TextureRegion(textSpriteSheet, 626, 3, 32, 43);
        e_lower = new TextureRegion(textSpriteSheet, 678, 144, 32, 31);
        e_upper = new TextureRegion(textSpriteSheet, 196, 146, 32, 43);
        equal = new TextureRegion(textSpriteSheet, 494, 221, 31, 26);
        exclamation_mark = new TextureRegion(textSpriteSheet, 787, 97, 7, 43);
        f_lower = new TextureRegion(textSpriteSheet, 770, 3, 25, 43);
        f_upper = new TextureRegion(textSpriteSheet, 662, 3, 32, 43);
        forward_dash = new TextureRegion(textSpriteSheet, 737, 179, 20, 40);
        g_lower = new TextureRegion(textSpriteSheet, 512, 50, 32, 38);
        g_upper = new TextureRegion(textSpriteSheet, 232, 146, 32, 43);
        gear_01 = new TextureRegion(textSpriteSheet, 427, 3, 81, 81);
        gear_02 = new TextureRegion(textSpriteSheet, 3, 146, 81, 81);
        h_lower = new TextureRegion(textSpriteSheet, 698, 3, 32, 43);
        h_upper = new TextureRegion(textSpriteSheet, 268, 146, 32, 43);
        hyphen = new TextureRegion(textSpriteSheet, 3, 231, 31, 7);
        i_lower = new TextureRegion(textSpriteSheet, 786, 144, 7, 43);
        i_upper = new TextureRegion(textSpriteSheet, 412, 146, 19, 43);
        j_lower = new TextureRegion(textSpriteSheet, 427, 88, 32, 50);
        j_upper = new TextureRegion(textSpriteSheet, 734, 3, 32, 43);
        k_lower = new TextureRegion(textSpriteSheet, 435, 142, 25, 43);
        k_upper = new TextureRegion(textSpriteSheet, 304, 146, 32, 43);
        l_lower = new TextureRegion(textSpriteSheet, 761, 179, 13, 43);
        l_upper = new TextureRegion(textSpriteSheet, 340, 146, 32, 43);
        m_lower = new TextureRegion(textSpriteSheet, 714, 144, 32, 31);
        m_upper = new TextureRegion(textSpriteSheet, 376, 146, 32, 43);
        n_lower = new TextureRegion(textSpriteSheet, 750, 144, 32, 31);
        n_upper = new TextureRegion(textSpriteSheet, 548, 50, 32, 43);
        number_0 = new TextureRegion(textSpriteSheet, 584, 50, 32, 43);
        number_1 = new TextureRegion(textSpriteSheet, 764, 50, 31, 43);
        number_2 = new TextureRegion(textSpriteSheet, 620, 50, 32, 43);
        number_3 = new TextureRegion(textSpriteSheet, 656, 50, 32, 43);
        number_4 = new TextureRegion(textSpriteSheet, 692, 50, 32, 43);
        number_5 = new TextureRegion(textSpriteSheet, 728, 50, 32, 43);
        number_6 = new TextureRegion(textSpriteSheet, 88, 193, 32, 43);
        number_7 = new TextureRegion(textSpriteSheet, 124, 193, 32, 43);
        number_8 = new TextureRegion(textSpriteSheet, 160, 193, 32, 43);
        number_9 = new TextureRegion(textSpriteSheet, 196, 193, 32, 43);
        o_lower = new TextureRegion(textSpriteSheet, 494, 186, 32, 31);
        o_upper = new TextureRegion(textSpriteSheet, 232, 193, 32, 43);
        p_lower = new TextureRegion(textSpriteSheet, 535, 97, 32, 38);
        p_upper = new TextureRegion(textSpriteSheet, 268, 193, 32, 43);
        parenthesis_left = new TextureRegion(textSpriteSheet, 412, 193, 26, 43);
        parenthesis_right = new TextureRegion(textSpriteSheet, 442, 189, 25, 43);
        period = new TextureRegion(textSpriteSheet, 549, 221, 7, 13);
        pound = new TextureRegion(textSpriteSheet, 464, 135, 31, 43);
        q_lower = new TextureRegion(textSpriteSheet, 571, 97, 32, 38);
        q_upper = new TextureRegion(textSpriteSheet, 304, 193, 32, 43);
        question_mark = new TextureRegion(textSpriteSheet, 340, 193, 32, 43);
        quotation_mark = new TextureRegion(textSpriteSheet, 529, 221, 16, 13);
        r_lower = new TextureRegion(textSpriteSheet, 530, 186, 32, 31);
        r_upper = new TextureRegion(textSpriteSheet, 376, 193, 32, 43);
        s_lower = new TextureRegion(textSpriteSheet, 570, 181, 32, 31);
        s_upper = new TextureRegion(textSpriteSheet, 499, 92, 32, 43);
        semi_colon = new TextureRegion(textSpriteSheet, 778, 191, 7, 38);
        t_lower = new TextureRegion(textSpriteSheet, 471, 182, 19, 43);
        t_upper = new TextureRegion(textSpriteSheet, 535, 139, 31, 43);
        text_background = new TextureRegion(textSpriteSheet, 535, 92, 1, 1);
        twitter_board_bottom = new TextureRegion(textSpriteSheet, 3, 77, 420, 65);
        twitter_board_top = new TextureRegion(textSpriteSheet, 3, 3, 420, 70);
        u_lower = new TextureRegion(textSpriteSheet, 566, 216, 32, 31);
        u_upper = new TextureRegion(textSpriteSheet, 607, 97, 32, 43);
        v_lower = new TextureRegion(textSpriteSheet, 602, 216, 32, 31);
        v_upper = new TextureRegion(textSpriteSheet, 643, 97, 32, 43);
        w_lower = new TextureRegion(textSpriteSheet, 606, 179, 32, 31);
        w_upper = new TextureRegion(textSpriteSheet, 679, 97, 32, 43);
        x_lower = new TextureRegion(textSpriteSheet, 642, 179, 32, 31);
        x_upper = new TextureRegion(textSpriteSheet, 715, 97, 32, 43);
        y_lower = new TextureRegion(textSpriteSheet, 570, 139, 32, 38);
        y_upper = new TextureRegion(textSpriteSheet, 751, 97, 32, 43);
        z_lower = new TextureRegion(textSpriteSheet, 678, 179, 31, 31);
        z_upper = new TextureRegion(textSpriteSheet, 499, 139, 32, 43);
    }


    public static void reload(){
        if (textSpriteSheet != null) {
            textSpriteSheet.reload();
        }
    }

    public static void dispose(){
        if (textSpriteSheet != null) {
            textSpriteSheet.dispose();
        }
    }

    public static void clear(){
        textSpriteSheet = null;

        a_lower = null;
        a_upper = null;
        apostrophe = null;
        at = null;
        b_lower = null;
        b_upper = null;
        backwards_dash = null;
        c_lower = null;
        c_upper = null;
        comma = null;
        d_lower = null;
        d_upper = null;
        dollar = null;
        e_lower = null;
        e_upper = null;
        equal = null;
        exclamation_mark = null;
        f_lower = null;
        f_upper = null;
        forward_dash = null;
        g_lower = null;
        g_upper = null;
        gear_01 = null;
        gear_02 = null;
        h_lower = null;
        h_upper = null;
        hyphen = null;
        i_lower = null;
        i_upper = null;
        j_lower = null;
        j_upper = null;
        k_lower = null;
        k_upper = null;
        l_lower = null;
        l_upper = null;
        m_lower = null;
        m_upper = null;
        n_lower = null;
        n_upper = null;
        number_0 = null;
        number_1 = null;
        number_2 = null;
        number_3 = null;
        number_4 = null;
        number_5 = null;
        number_6 = null;
        number_7 = null;
        number_8 = null;
        number_9 = null;
        o_lower = null;
        o_upper = null;
        p_lower = null;
        p_upper = null;
        parenthesis_left = null;
        parenthesis_right = null;
        period = null;
        pound = null;
        q_lower = null;
        q_upper = null;
        question_mark = null;
        quotation_mark = null;
        r_lower = null;
        r_upper = null;
        s_lower = null;
        s_upper = null;
        semi_colon = null;
        t_lower = null;
        t_upper = null;
        text_background = null;
        twitter_board_bottom = null;
        twitter_board_top = null;
        u_lower = null;
        u_upper = null;
        v_lower = null;
        v_upper = null;
        w_lower = null;
        w_upper = null;
        x_lower = null;
        x_upper = null;
        y_lower = null;
        y_upper = null;
        z_lower = null;
        z_upper = null;
    }

}
