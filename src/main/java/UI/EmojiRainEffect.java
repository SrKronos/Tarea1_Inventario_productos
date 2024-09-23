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
import java.util.Iterator;
import java.util.Random;

public class EmojiRainEffect {
    private ArrayList<Emoji> emojis;
    private Timer timer;
    private Random random;
    private JPanel targetPanel;
    private String[] emojiList = {
        "😊", "😋", "😁", "😍", "😄", "😃", "🙂", "😌", "😇", "🤤", "🤩", "🥰"
    };

    public EmojiRainEffect(JPanel panel) {
        this.targetPanel = panel;
        emojis = new ArrayList<>();
        random = new Random();
        timer = new Timer(20, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (random.nextFloat() < 0.1) {
                    addEmoji();
                }
                updateEmojis();
                targetPanel.repaint();
            }
        });
    }

    public void start() {
        timer.start();
        targetPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                targetPanel.repaint();
            }
        });
        targetPanel.addPropertyChangeListener("UI", evt -> targetPanel.repaint());
    }

    public void stop() {
        timer.stop();
        targetPanel.repaint();
    }

    private void addEmoji() {
        String emoji = emojiList[random.nextInt(emojiList.length)];
        int x = random.nextInt(targetPanel.getWidth());
        emojis.add(new Emoji(emoji, x, -20));
    }

    private void updateEmojis() {
        Iterator<Emoji> iterator = emojis.iterator();
        while (iterator.hasNext()) {
            Emoji emoji = iterator.next();
            emoji.update();
            if (emoji.y > targetPanel.getHeight()) {
                iterator.remove();
            }
        }
    }

    public void paintEmojis(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        for (Emoji emoji : emojis) {
            emoji.draw(g2d);
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
            this.vy = 1 + random.nextFloat() * 2; // Velocidad inicial aleatoria
        }

        void update() {
            y += vy;
        }

        void draw(Graphics2D g2d) {
            g2d.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 20));
            g2d.drawString(emoji, x, y);
        }
    }
}