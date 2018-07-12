package com.tp.batman.francis.blockgame.game.Assets;

import com.tp.batman.francis.blockgame.framework.gl.SpriteBatcher;
import com.tp.batman.francis.blockgame.game.Assets.Character;

/**
 * Created by Francis on 2017-04-26.
 */
public class Text {

    private String text;
    private float x, y;
    private float width, height;
    private final float lineSpacing = 10f;
    private float backgroundPadding = 20;
    private boolean invisible;
    private boolean background;
    private float backgroundWidth, backgroundHeight;
    private float backgroundX, backgroundY;
    private Line lines[];
    private Word words[];
    private int wordCount, lineCount;
    private Character characters[];
    private char charCharacters[];
    private int size;
    private String colour;
    private String alignment;

    public Text(String text, int size, String colour, float x, float y, float width) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.size = size;
        this.colour = colour;

        alignment = "left";
        background = false;
        backgroundHeight = 0;
        height = 0;
        invisible = false;

        if (size > 10) {
            backgroundPadding = backgroundPadding * ((size / 10) * (size / 10));
        }
        create();
    }

    public Text(String text, int size, String colour, String alignment, float x, float y, float width) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.size = size;
        this.colour = colour;
        this.alignment = alignment;

        background = false;
        backgroundHeight = 0;
        height = 0;
        invisible = false;

        if (size > 10) {
            backgroundPadding = backgroundPadding * ((size / 10) * (size / 10));
        }
        create();
    }

    public Text(String text, int size, String colour, float x, float y, float width, boolean background, float backgroundHeight) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.background = background;
        this.backgroundHeight = backgroundHeight;
        this.size = size;
        this.colour = colour;

        alignment = "left";
        height = 0;
        invisible = false;

        if (size > 10) {
            backgroundPadding = backgroundPadding * ((size / 10) * (size / 10));
        }
        create();
    }

    public Text(String text, int size, String colour, String alignment, float x, float y, float width, boolean background, float backgroundHeight) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.background = background;
        this.backgroundHeight = backgroundHeight;
        this.size = size;
        this.colour = colour;
        this.alignment = alignment;

        height = 0;
        invisible = false;

        if (size > 10) {
            backgroundPadding = backgroundPadding * ((size / 10) * (size / 10));
        }
        create();
    }

    public Text(String text, int size, String colour, float x, float y, float width, boolean background) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.background = background;
        this.backgroundHeight = -1;
        this.size = size;
        this.colour = colour;

        alignment = "left";
        height = 0;
        invisible = false;

        if (size > 10) {
            backgroundPadding = backgroundPadding * ((size / 10) * (size / 10));
        }
        create();
    }

    public Text(String text, int size, String colour, String alignment, float x, float y, float width, boolean background) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.width = width;
        this.background = background;
        this.backgroundHeight = -1;
        this.size = size;
        this.colour = colour;
        this.alignment = alignment;

        height = 0;
        invisible = false;

        if (size > 10) {
            backgroundPadding = backgroundPadding * ((size / 10) * (size / 10));
        }
        create();
    }

    private void create() {
        wordCount = 0;
        lineCount = 0;
        charCharacters = text.toCharArray();
        lines = new Line[charCharacters.length];
        words = new Word[charCharacters.length];
        characters = new Character[charCharacters.length];

        // Create characters
        float wordHeight = 0;
        for (int i=0; i<charCharacters.length; i ++) {
            characters[i] = new Character(charCharacters[i], size, colour);
            if (characters[i].getHeight() > wordHeight) {
                wordHeight = characters[i].getHeight();
            }
        }

        // All characters created, word creation starts
        Character wordChar[] = new Character[characters.length];
        int wordCharCount = 0;
        for (int i=0; i<characters.length; i ++) {
            // making words
            if (i == characters.length-1) { // last character in text
                if (characters[i].getCharacter() == ' ') {
                    if (wordChar[0] == null) {
                        words[wordCount] = new Word(new Character[]{characters[i]}, characters[i].getWidth(), wordHeight);
                        wordCount++;
                    } else {
                        Character[] newWordChar = new Character[wordCharCount];
                        wordCharCount = 0;

                        // create word
                        float wordWidth = 0;
                        for (int r = 0; r < newWordChar.length; r++) {
                            if (r == newWordChar.length - 1) {
                                newWordChar[r] = wordChar[r];
                                wordWidth += newWordChar[r].getWidth();
                            } else {
                                newWordChar[r] = wordChar[r];
                                wordWidth += newWordChar[r].getWidth() + Word.charSpacing;
                            }
                        }
                        words[wordCount] = new Word(newWordChar, wordWidth, wordHeight);
                        wordCount++;
                        wordChar = new Character[characters.length];

                        // create "space" word
                        words[wordCount] = new Word(new Character[]{characters[i]}, characters[i].getWidth(), wordHeight);
                        wordCount++;
                    }
                } else {
                    wordChar[wordCharCount] = characters[i];
                    wordCharCount++;

                    Character[] newWordChar = new Character[wordCharCount];
                    wordCharCount = 0;

                    // create word
                    float wordWidth = 0;
                    for (int r = 0; r < newWordChar.length; r++) {
                        if (r == newWordChar.length - 1) {
                            newWordChar[r] = wordChar[r];
                            wordWidth += newWordChar[r].getWidth();
                        } else {
                            newWordChar[r] = wordChar[r];
                            wordWidth += newWordChar[r].getWidth() + Word.charSpacing;
                        }
                    }
                    words[wordCount] = new Word(newWordChar, wordWidth, wordHeight);
                    wordCount++;
                    wordChar = new Character[characters.length];
                }
            } else if (characters[i].getCharacter() == ' ') { // space
                if (wordChar[0] == null) {
                    words[wordCount] = new Word(new Character[]{characters[i]}, characters[i].getWidth(), wordHeight);
                    wordCount++;
                } else {
                    Character[] newWordChar = new Character[wordCharCount];
                    wordCharCount = 0;

                    // create word
                    float wordWidth = 0;
                    for (int r = 0; r < newWordChar.length; r++) {
                        if (r == newWordChar.length - 1) {
                            newWordChar[r] = wordChar[r];
                            wordWidth += newWordChar[r].getWidth();
                        } else {
                            newWordChar[r] = wordChar[r];
                            wordWidth += newWordChar[r].getWidth() + Word.charSpacing;
                        }
                    }
                    words[wordCount] = new Word(newWordChar, wordWidth, wordHeight);
                    wordCount++;
                    wordChar = new Character[characters.length];

                    // create "space" word
                    words[wordCount] = new Word(new Character[]{characters[i]}, characters[i].getWidth(), wordHeight);
                    wordCount++;
                }
            } else { // character
                wordChar[wordCharCount] = characters[i];
                wordCharCount++;
            }
        }

        Word[] newWords = new Word[wordCount];
        for (int i=0; i<wordCount; i++) {
            newWords[i] = words[i];
        }
        words = newWords;

        // All words created, line creation starts
        Word[] lineWords = new Word[words.length];
        int wordCounter = 0;
        Word overFlowWord = null; // used if word is bigger than defined width of text
        float lineWidth = 0;
        for (int i=0; i<words.length; i++) {
            if (overFlowWord != null) { // i index is on the word that overflowed
                // there is an overflow word
                if (overFlowWord.getWidth() > width) { // Over word still larger than width

                    Character hyphen = new Character('-', size, colour);
                    float widthLeft = width - lineWidth - hyphen.getWidth();
                    int lettersThatFit = 0;
                    while (widthLeft - overFlowWord.getCharacters()[lettersThatFit].getWidth() > 0) {
                        widthLeft -= overFlowWord.getCharacters()[lettersThatFit].getWidth() + Word.charSpacing;
                        lettersThatFit++;
                    }

                    if (lettersThatFit >=1) { // just need one to fit
                        i--; // this is here so we stay on same word until it is completely added

                        Character[] partialWordCharacters = new Character[lettersThatFit + 1]; // create partial word and create line with it, set new partial word to remaining characters
                        float partialWordWidth = 0;
                        for (int r = 0; r < partialWordCharacters.length; r++) {
                            if (r == partialWordCharacters.length - 1) {
                                partialWordCharacters[r] = hyphen;
                                partialWordWidth += partialWordCharacters[r].getWidth();
                            } else {
                                partialWordCharacters[r] = overFlowWord.getCharacters()[r];
                                partialWordWidth += partialWordCharacters[r].getWidth() + Word.charSpacing;
                            }
                        }
                        Word newPartialWord; // word add at the end of this line
                        newPartialWord = new Word(partialWordCharacters, partialWordWidth, wordHeight);
                        Character[] newOverFlowWordCharacters = new Character[overFlowWord.getCharacters().length - lettersThatFit];
                        lineWidth += newPartialWord.getWidth();

                        float overFlowWordWidth = 0;
                        for (int r = 0; r < newOverFlowWordCharacters.length; r++) {
                            newOverFlowWordCharacters[r] = overFlowWord.getCharacters()[lettersThatFit + r];
                            if (r == newOverFlowWordCharacters.length - 1) {
                                overFlowWordWidth += newOverFlowWordCharacters[r].getWidth();
                            } else {
                                overFlowWordWidth += newOverFlowWordCharacters[r].getWidth() + Word.charSpacing;
                            }
                        }
                        overFlowWord = new Word(newOverFlowWordCharacters, overFlowWordWidth, wordHeight); // word for next line set

                        // We have overFlow word for the next line and the partial overFlow word for the current line

                        wordCounter++;
                        if (lineWords.length <= wordCount) {
                            lineWords[wordCounter - 1] = newPartialWord;
                        } else {
                            Word[] longerLineWords = new Word[wordCounter];
                            for (int r = 0; r < wordCounter - 2; r++) {
                                longerLineWords[r] = lineWords[r];
                            }
                            longerLineWords[wordCounter - 1] = newPartialWord;
                            lineWords = longerLineWords;
                        }

                        Word[] newLineWords = null;
                        boolean hasBeginingSpace = false;
                        boolean hasEndingSpace = false;

                        if (lineWords[0].getCharacters()[0].getCharacter() == ' ') {
                            hasBeginingSpace = true;
                            lineWidth -= lineWords[0].getCharacters()[0].getWidth();
                        }
                        if (lineWords[wordCounter - 1].getCharacters()[0].getCharacter() == ' ') {
                            hasEndingSpace = true;
                            lineWidth -= lineWords[wordCounter - 1].getCharacters()[0].getWidth();
                        }
                        if (!hasBeginingSpace && !hasEndingSpace) {
                            // no space
                            newLineWords = new Word[wordCounter];

                        } else if (hasBeginingSpace && !hasEndingSpace) {
                            // beginning space
                            newLineWords = new Word[wordCounter - 1];

                        } else if (!hasBeginingSpace && hasEndingSpace) {
                            // ending space
                            newLineWords = new Word[wordCounter - 1];

                        } else if (hasBeginingSpace && hasEndingSpace) {
                            // beginning and ending space
                            newLineWords = new Word[wordCounter - 2];
                        } else {
                            newLineWords = new Word[wordCounter];
                        }


                        for (int r = 0; r < wordCounter; r++) {
                            // check for space at beginning and end, omit them if present
                            if (wordCounter >= 1) {
                                if (!hasBeginingSpace && !hasEndingSpace) {
                                    // no space
                                    newLineWords[r] = lineWords[r];

                                } else if (hasBeginingSpace && !hasEndingSpace) {
                                    // beginning space
                                    if (r != 0) {
                                        newLineWords[r] = lineWords[r - 1];
                                    }

                                } else if (!hasBeginingSpace && hasEndingSpace) {
                                    // ending space
                                    if (r != wordCounter - 1) {
                                        newLineWords[r] = lineWords[r];
                                    }

                                } else if (hasBeginingSpace && hasEndingSpace) {
                                    // beginning and ending space
                                    if (r != 0 && r != wordCounter - 1) {
                                        newLineWords[r] = lineWords[r - 1];
                                    }
                                } else {
                                    newLineWords[r] = lineWords[r];
                                }
                            } else {
                                newLineWords[r] = lineWords[r];
                            }
                        }

                        Line newLine = new Line(newLineWords, lineWidth, wordHeight);
                        lines[lineCount] = newLine;
                        lineCount++;
                        lineWidth = 0;
                        wordCounter = 0;
                        lineWords = new Word[words.length];
                    } else {
                        // if not even one character fits, omit the entire word
                        System.out.println("AN ENTIRE WORD WAS OMITTED");
                        lineWidth = 0;
                        wordCounter = 0;
                        lineWords = new Word[words.length];
                        overFlowWord = null;
                    }

                } else { // add overflow word like regular word
                    lineWords[wordCounter] = overFlowWord;
                    wordCounter++;
                    lineWidth += overFlowWord.getWidth();
                    overFlowWord = null;

                    if (i == words.length-1) {
                        // create line
                        Word[] newLineWords = null;
                        boolean hasBeginingSpace = false;
                        boolean hasEndingSpace = false;

                        if (lineWords[0].getCharacters()[0].getCharacter() == ' ') {
                            hasBeginingSpace = true;
                            lineWidth -= lineWords[0].getCharacters()[0].getWidth();
                        }
                        if (lineWords[wordCounter-1].getCharacters()[0].getCharacter() == ' ') {
                            hasEndingSpace = true;
                            lineWidth -= lineWords[wordCounter - 1].getCharacters()[0].getWidth();
                        }
                        if (!hasBeginingSpace && !hasEndingSpace) {
                            // no space
                            newLineWords = new Word[wordCounter];

                        } else if (hasBeginingSpace && !hasEndingSpace) {
                            // beginning space
                            newLineWords = new Word[wordCounter-1];

                        } else if (!hasBeginingSpace && hasEndingSpace) {
                            // ending space
                            newLineWords = new Word[wordCounter-1];

                        } else if (hasBeginingSpace && hasEndingSpace) {
                            // beginning and ending space
                            newLineWords = new Word[wordCounter-2];
                        } else {
                            newLineWords = new Word[wordCounter];
                        }


                        for (int r=0; r < wordCounter; r++) {
                            // check for space at beginning and end, omit them if present
                            if (wordCounter >= 1) {
                                if (!hasBeginingSpace && !hasEndingSpace) {
                                    // no space
                                    newLineWords[r] = lineWords[r];

                                } else if (hasBeginingSpace && !hasEndingSpace) {
                                    // beginning space
                                    if (r != 0) {
                                        newLineWords[r-1] = lineWords[r-1];
                                    }

                                } else if (!hasBeginingSpace && hasEndingSpace) {
                                    // ending space
                                    if (r != wordCounter-1) {
                                        newLineWords[r] = lineWords[r];
                                    }

                                } else if (hasBeginingSpace && hasEndingSpace) {
                                    // beginning and ending space
                                    if (r != 0 && r != wordCounter-1) {
                                        newLineWords[r-1] = lineWords[r-1];
                                    }
                                } else {
                                    newLineWords[r] = lineWords[r];
                                }
                            } else {
                                newLineWords[r] = lineWords[r];
                            }
                        }

                        Line newLine = new Line(newLineWords, lineWidth, wordHeight);
                        lines[lineCount] = newLine;
                        lineCount++;
                        lineWidth = 0;
                        wordCounter = 0;
                        lineWords = new Word[words.length];
                    }
                }
            } else {    // DONE
                if (words[i] != null) {

                    if (lineWidth + words[i].getWidth() > width) {
                        if (words[i].getWidth() > width) { // word needs to be broken up
                            overFlowWord = words[i];
                            // roll back to previous word
                            i--;

                            // look if part of word can fit on line
                            Character hyphen = new Character('-', size, colour);
                            float widthLeft = width - lineWidth - hyphen.getWidth();
                            int lettersThatFit = 0;
                            while (widthLeft - overFlowWord.getCharacters()[lettersThatFit].getWidth() > 0) {
                                widthLeft -= overFlowWord.getCharacters()[lettersThatFit].getWidth() + Word.charSpacing;
                                lettersThatFit++;
                            }

                            if (lettersThatFit > 3) { // at least three characters fit on the line

                                Character[] partialWordCharacters = new Character[lettersThatFit+1]; // create partial word and create line with it, set new partial word to remaining characters
                                float partialWordWidth = 0;
                                for (int r=0; r<partialWordCharacters.length; r++) {
                                    if (r == partialWordCharacters.length-1) {
                                        partialWordCharacters[r] = hyphen;
                                        partialWordWidth += partialWordCharacters[r].getWidth();
                                    } else {
                                        partialWordCharacters[r] = overFlowWord.getCharacters()[r];
                                        partialWordWidth += partialWordCharacters[r].getWidth() + Word.charSpacing;
                                    }
                                }
                                Word newPartialWord; // word add at the end of this line
                                newPartialWord = new Word(partialWordCharacters, partialWordWidth, wordHeight);
                                Character[] newOverFlowWordCharacters = new Character[overFlowWord.getCharacters().length - lettersThatFit];
                                lineWidth += newPartialWord.getWidth();

                                float overFlowWordWidth = 0;
                                for (int r=0; r< newOverFlowWordCharacters.length; r++) {
                                    newOverFlowWordCharacters[r] = overFlowWord.getCharacters()[lettersThatFit + r];
                                    if (r == newOverFlowWordCharacters.length-1) {
                                        overFlowWordWidth += newOverFlowWordCharacters[r].getWidth();
                                    } else {
                                        overFlowWordWidth += newOverFlowWordCharacters[r].getWidth() + Word.charSpacing;
                                    }
                                }
                                overFlowWord = new Word(newOverFlowWordCharacters, overFlowWordWidth, wordHeight); // word for next line set

                                // We have overFlow word for the next line and the partial overFlow word for the current line

                                wordCounter++;
                                if (lineWords.length <= wordCount) {
                                    lineWords[wordCounter - 1] = newPartialWord;
                                } else {
                                    Word[] longerLineWords = new Word[wordCounter];
                                    for (int r=0; r<wordCounter-2; r++) {
                                        longerLineWords[r] = lineWords[r];
                                    }
                                    longerLineWords[wordCounter - 1] = newPartialWord;
                                    lineWords = longerLineWords;
                                }

                                Word[] newLineWords = null;
                                boolean hasBeginingSpace = false;
                                boolean hasEndingSpace = false;

                                if (lineWords[0].getCharacters()[0].getCharacter() == ' ') {
                                    hasBeginingSpace = true;
                                    lineWidth -= lineWords[0].getCharacters()[0].getWidth();
                                }
                                if (lineWords[wordCounter-1].getCharacters()[0].getCharacter() == ' ') {
                                    hasEndingSpace = true;
                                    lineWidth -= lineWords[wordCounter - 1].getCharacters()[0].getWidth();
                                }
                                if (!hasBeginingSpace && !hasEndingSpace) {
                                    // no space
                                    newLineWords = new Word[wordCounter];

                                } else if (hasBeginingSpace && !hasEndingSpace) {
                                    // beginning space
                                    newLineWords = new Word[wordCounter-1];

                                } else if (!hasBeginingSpace && hasEndingSpace) {
                                    // ending space
                                    newLineWords = new Word[wordCounter-1];

                                } else if (hasBeginingSpace && hasEndingSpace) {
                                    // beginning and ending space
                                    newLineWords = new Word[wordCounter-2];
                                } else {
                                    newLineWords = new Word[wordCounter];
                                }


                                for (int r=0; r < wordCounter; r++) {
                                    // check for space at beginning and end, omit them if present
                                    if (wordCounter >= 1) {
                                        if (!hasBeginingSpace && !hasEndingSpace) {
                                            // no space
                                            newLineWords[r] = lineWords[r];

                                        } else if (hasBeginingSpace && !hasEndingSpace) {
                                            // beginning space
                                            if (r != 0) {
                                                newLineWords[r-1] = lineWords[r];
                                            }

                                        } else if (!hasBeginingSpace && hasEndingSpace) {
                                            // ending space
                                            if (r != wordCounter-1) {
                                                newLineWords[r] = lineWords[r];
                                            }

                                        } else if (hasBeginingSpace && hasEndingSpace) {
                                            // beginning and ending space
                                            if (r != 0 && r != wordCounter-1) {
                                                newLineWords[r-1] = lineWords[r];
                                            }
                                        } else {
                                            newLineWords[r] = lineWords[r];
                                        }
                                    } else {
                                        newLineWords[r] = lineWords[r];
                                    }
                                }

                                Line newLine = new Line(newLineWords, lineWidth, wordHeight);
                                lines[lineCount] = newLine;
                                lineCount++;
                                lineWidth = 0;
                                wordCounter = 0;
                                lineWords = new Word[words.length];

                            } else {        // DONE
                                Word[] newLineWords = null;
                                boolean hasBeginingSpace = false;
                                boolean hasEndingSpace = false;

                                if (lineWords[0].getCharacters()[0].getCharacter() == ' ') {
                                    hasBeginingSpace = true;
                                    lineWidth -= lineWords[0].getCharacters()[0].getWidth();
                                }
                                if (lineWords[wordCounter-1].getCharacters()[0].getCharacter() == ' ') {
                                    hasEndingSpace = true;
                                    lineWidth -= lineWords[wordCounter - 1].getCharacters()[0].getWidth();
                                }
                                if (!hasBeginingSpace && !hasEndingSpace) {
                                    // no space
                                    newLineWords = new Word[wordCounter];

                                } else if (hasBeginingSpace && !hasEndingSpace) {
                                    // beginning space
                                    newLineWords = new Word[wordCounter-1];

                                } else if (!hasBeginingSpace && hasEndingSpace) {
                                    // ending space
                                    newLineWords = new Word[wordCounter-1];

                                } else if (hasBeginingSpace && hasEndingSpace) {
                                    // beginning and ending space
                                    newLineWords = new Word[wordCounter-2];
                                } else {
                                    newLineWords = new Word[wordCounter];
                                }


                                for (int r=0; r < wordCounter; r++) {
                                    // check for space at beginning and end, omit them if present
                                    if (wordCounter >= 1) {
                                        if (!hasBeginingSpace && !hasEndingSpace) {
                                            // no space
                                            newLineWords[r] = lineWords[r];

                                        } else if (hasBeginingSpace && !hasEndingSpace) {
                                            // beginning space
                                            if (r != 0) {
                                                newLineWords[r-1] = lineWords[r];
                                            }

                                        } else if (!hasBeginingSpace && hasEndingSpace) {
                                            // ending space
                                            if (r != wordCounter-1) {
                                                newLineWords[r] = lineWords[r];
                                            }

                                        } else if (hasBeginingSpace && hasEndingSpace) {
                                            // beginning and ending space
                                            if (r != 0 && r != wordCounter-1) {
                                                newLineWords[r-1] = lineWords[r];
                                            }
                                        } else {
                                            newLineWords[r] = lineWords[r];
                                        }
                                    } else {
                                        newLineWords[r] = lineWords[r];
                                    }
                                }

                                Line newLine = new Line(newLineWords, lineWidth, wordHeight);
                                lines[lineCount] = newLine;
                                lineCount++;
                                lineWidth = 0;
                                wordCounter = 0;
                                lineWords = new Word[words.length];
                            }

                        } else { // word goes on next line      // DONE
                            // roll back to previous word
                            i--;
                            // create next line
                            Word[] newLineWords = null;
                            boolean hasBeginingSpace = false;
                            boolean hasEndingSpace = false;

                            if (lineWords[0].getCharacters()[0].getCharacter() == ' ') {
                                hasBeginingSpace = true;
                                lineWidth -= lineWords[0].getCharacters()[0].getWidth();
                            }
                            if (lineWords[wordCounter-1].getCharacters()[0].getCharacter() == ' ') {
                                hasEndingSpace = true;
                                lineWidth -= lineWords[wordCounter - 1].getCharacters()[0].getWidth();
                            }
                            if (!hasBeginingSpace && !hasEndingSpace) {
                                // no space
                                newLineWords = new Word[wordCounter];

                            } else if (hasBeginingSpace && !hasEndingSpace) {
                                // beginning space
                                newLineWords = new Word[wordCounter-1];

                            } else if (!hasBeginingSpace && hasEndingSpace) {
                                // ending space
                                newLineWords = new Word[wordCounter-1];

                            } else if (hasBeginingSpace && hasEndingSpace) {
                                // beginning and ending space
                                newLineWords = new Word[wordCounter-2];
                            } else {
                                newLineWords = new Word[wordCounter];
                            }


                            for (int r=0; r < wordCounter; r++) {
                                // check for space at beginning and end, omit them if present
                                if (wordCounter >= 1) {
                                    if (!hasBeginingSpace && !hasEndingSpace) {
                                        // no space
                                        newLineWords[r] = lineWords[r];

                                    } else if (hasBeginingSpace && !hasEndingSpace) {
                                        // beginning space
                                        if (r != 0) {
                                            newLineWords[r-1] = lineWords[r];
                                        }

                                    } else if (!hasBeginingSpace && hasEndingSpace) {
                                        // ending space
                                        if (r != wordCounter-1) {
                                            newLineWords[r] = lineWords[r];
                                        }

                                    } else if (hasBeginingSpace && hasEndingSpace) {
                                        // beginning and ending space
                                        if (r != 0 && r != wordCounter-1) {
                                            newLineWords[r-1] = lineWords[r];
                                        }
                                    } else {
                                        newLineWords[r] = lineWords[r];
                                    }
                                } else {
                                    newLineWords[r] = lineWords[r];
                                }
                            }

                            Line newLine = new Line(newLineWords, lineWidth, wordHeight);
                            lines[lineCount] = newLine;
                            lineCount++;
                            lineWidth = 0;
                            wordCounter = 0;
                            lineWords = new Word[words.length];
                        }
                    } else { // add word to line        // DONE
                        lineWords[wordCounter] = words[i];
                        wordCounter++;
                        lineWidth += words[i].getWidth();

                        if (i == words.length-1) {
                            // create line
                            Word[] newLineWords = null;
                            boolean hasBeginingSpace = false;
                            boolean hasEndingSpace = false;

                            if (lineWords[0].getCharacters()[0].getCharacter() == ' ') {
                                hasBeginingSpace = true;
                                lineWidth -= lineWords[0].getCharacters()[0].getWidth();
                            }
                            if (lineWords[wordCounter-1].getCharacters()[0].getCharacter() == ' ') {
                                hasEndingSpace = true;
                                lineWidth -= lineWords[wordCounter - 1].getCharacters()[0].getWidth();
                            }
                            if (!hasBeginingSpace && !hasEndingSpace) {
                                // no space
                                newLineWords = new Word[wordCounter];

                            } else if (hasBeginingSpace && !hasEndingSpace) {
                                // beginning space
                                newLineWords = new Word[wordCounter-1];

                            } else if (!hasBeginingSpace && hasEndingSpace) {
                                // ending space
                                newLineWords = new Word[wordCounter-1];

                            } else if (hasBeginingSpace && hasEndingSpace) {
                                // beginning and ending space
                                newLineWords = new Word[wordCounter-2];
                            } else {
                                newLineWords = new Word[wordCounter];
                            }


                            for (int r=0; r < wordCounter; r++) {
                                // check for space at beginning and end, omit them if present
                                if (wordCounter >= 1) {
                                    if (!hasBeginingSpace && !hasEndingSpace) {
                                        // no space
                                        newLineWords[r] = lineWords[r];

                                    } else if (hasBeginingSpace && !hasEndingSpace) {
                                        // beginning space
                                        if (r != 0) {
                                            newLineWords[r-1] = lineWords[r];
                                        }

                                    } else if (!hasBeginingSpace && hasEndingSpace) {
                                        // ending space
                                        if (r != wordCounter-1) {
                                            newLineWords[r] = lineWords[r];
                                        }

                                    } else if (hasBeginingSpace && hasEndingSpace) {
                                        // beginning and ending space
                                        if (r != 0 && r != wordCounter-1) {
                                            newLineWords[r-1] = lineWords[r];
                                        }
                                    } else {
                                        newLineWords[r] = lineWords[r];
                                    }
                                } else {
                                    newLineWords[r] = lineWords[r];
                                }
                            }

                            Line newLine = new Line(newLineWords, lineWidth, wordHeight);
                            lines[lineCount] = newLine;
                            lineCount++;
                            lineWidth = 0;
                            wordCounter = 0;
                            lineWords = new Word[words.length];
                        }
                    }
                } else {
                    System.out.println("A word was null.. Not suppose to happen");
                }
            }
        }

        Line[] newLines = new Line[lineCount];
        for (int i=0; i<lineCount; i++) {
            newLines[i] = lines[i];
        }
        lines = newLines;

        boolean calcBackHeight = false;
        if (background && backgroundHeight < 0) {
            calcBackHeight = true;
            backgroundHeight = 0;
        }
        // characters, words and lines are created - need to position them

        for (int i=0; i<lineCount; i++) {
            height += lines[i].getHeight() + lineSpacing;
            if (alignment == "left") {
                lines[i].setX(x);
                lines[i].setY(y - ((lines[i].getHeight() + lineSpacing) * i));
            } else if (alignment == "center") {
                float xOffset = (width - lines[i].getWidth())/2;
                lines[i].setX(x + xOffset);
                lines[i].setY(y - ((lines[i].getHeight() + lineSpacing) * i));
            } else if (alignment == "right") {
                float xOffset = width - lines[i].getWidth();
                lines[i].setX(x + xOffset);
                lines[i].setY(y - ((lines[i].getHeight() + lineSpacing) * i));
            } else {
                lines[i].setX(x);
                lines[i].setY(y - ((lines[i].getHeight() + lineSpacing) * i));
            }
            if (calcBackHeight) {
                if (i == lineCount-1) {
                    backgroundHeight += lines[i].getHeight();
                } else {
                    backgroundHeight += lines[i].getHeight() + lineSpacing;
                }
            }
        }

        if (calcBackHeight) {
            backgroundHeight += backgroundPadding;
        }
        backgroundWidth = width + backgroundPadding;

        backgroundX = (x - (backgroundPadding/2)) + (backgroundWidth/2);
        backgroundY = (y + (backgroundPadding/2)) - (backgroundHeight/2) + lines[0].getHeight();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        create();
    }

    public void addY(float amount) {
        for (int i=0; i<lines.length; i++) {
            lines[i].addY(amount);
        }
        y += amount;
        backgroundY += amount;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
        create();
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
        create();
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
        create();
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
        create();
    }

    public boolean isInvisible() {
        return invisible;
    }

    public void setInvisible(boolean invisible) {
        this.invisible = invisible;
    }

    public boolean isBackground() {
        return background;
    }

    public void setBackground(boolean background) {
        this.background = background;
    }

    public float getBackgroundWidth() {
        return backgroundWidth;
    }

    public void setBackgroundWidth(float backgroundWidth) {
        this.backgroundWidth = backgroundWidth;
    }

    public float getBackgroundHeight() {
        return backgroundHeight;
    }

    public void setBackgroundHeight(float backgroundHeight) {
        this.backgroundHeight = backgroundHeight;
    }

    public Line[] getLines() {
        return lines;
    }

    public void setLines(Line[] lines) {
        this.lines = lines;
    }

    public void render(SpriteBatcher batcher) {
        if (!invisible) {
            batcher.beginBatch(Assets.textAssets.textSpriteSheet);
            if (background) {
                batcher.drawSprite(backgroundX, backgroundY, backgroundWidth, backgroundHeight, Assets.textAssets.text_background);
            }

            for (int i = 0; i < lines.length; i++) {
                lines[i].render(batcher);
            }
            batcher.endBatch();
        }
    }

    public float getBackgroundY() {
        return backgroundY;
    }
}
