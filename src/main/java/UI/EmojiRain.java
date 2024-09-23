/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UI;

/**
 *
 * @author Leonardo Loor <leonardoloor1988@gmail.com>
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class EmojiRain extends JPanel implements ActionListener {
    private ArrayList<Emoji> emojis;
    private Timer timer;
    private Random random;
    private String[] emojiList = {
        "😊", "😋", "😁", "😍", "😄", "😃", "🙂", "😌", "😇", "🤤", "🤩", "🥰"
    };

    public EmojiRain() {
        emojis = new ArrayList<>();
        random = new Random();
        timer = new Timer(20, this);
        timer.start();
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        for (Emoji emoji : emojis) {
            emoji.draw(g2d);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (random.nextFloat() < 0.1) {
            addEmoji();
        }
        updateEmojis();
        repaint();
    }

    private void addEmoji() {
        String emoji = emojiList[random.nextInt(emojiList.length)];
        int x = random.nextInt(getWidth());
        emojis.add(new Emoji(emoji, x, -20));
    }

    private void updateEmojis() {
        for (Emoji emoji : emojis) {
            emoji.update();
            if (emoji.y > getHeight() - 20) {
                emoji.y = getHeight() - 20;
                emoji.vy = 0;
            }
        }
    }

    private class Emoji {
        String emoji;
        float x, y;
        float vy;

        Emoji(String emoji, float x, float y) {
            this.emoji = emoji;
            this.x = x;
            this.y = y;
            this.vy = 0;
        }

        void update() {
            vy += 0.2f; // Gravity
            y += vy;
        }

        void draw(Graphics2D g2d) {
            g2d.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 20));
            g2d.drawString(emoji, x, y);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Emoji Rain");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);
        frame.add(new EmojiRain());
        frame.setVisible(true);
    }
}
