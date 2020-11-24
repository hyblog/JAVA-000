package com.ipman.work06java8.gof.adapter;

/**
 * Created by ipipman on 2020/11/24.
 *
 * @version V1.0
 * @Package com.ipman.work06java8.gof.adapter
 * @Description: (用一句话描述该文件做什么)
 * @date 2020/11/24 2:17 下午
 */
public class AdapterDemo {


    //为媒体播放器和更高级的媒体播放器创建接口。
    public interface MediaPlayer {
        public void play(String audioType, String filName);
    }

    public interface AdvanceMediaPlayer {
        public void playVlc(String fileName);

        public void playMp4(String fileName);
    }

    //创建实现了 AdvancedMediaPlayer 接口的实体类。
    public static class VlcPlayper implements AdvanceMediaPlayer {
        @Override
        public void playVlc(String fileName) {
            System.out.println("Playing vlc file. Name: " + fileName);
        }

        @Override
        public void playMp4(String fileName) {
            //什么也不做
        }
    }

    public static class Mp4Player implements AdvanceMediaPlayer {
        @Override
        public void playVlc(String fileName) {
            //什么也不做
        }

        @Override
        public void playMp4(String fileName) {
            System.out.println("Playing mp4 file. Name: " + fileName);
        }
    }

    //创建实现了 MediaPlayer 接口的适配器类。
    public static class MediaAdpter implements MediaPlayer {

        AdvanceMediaPlayer advanceMediaPlayer;

        public MediaAdpter(String audioType) {
            if (audioType.equalsIgnoreCase("vlc")) {
                advanceMediaPlayer = new VlcPlayper();
            } else {
                advanceMediaPlayer = new Mp4Player();
            }
        }

        @Override
        public void play(String audioType, String fileName) {
            if (audioType.equalsIgnoreCase("vlc")) {
                advanceMediaPlayer.playVlc(fileName);
            } else {
                advanceMediaPlayer.playMp4(fileName);
            }
        }
    }

    //创建实现了 MediaPlayer 接口的实体类。
    public static class AudioPlayer implements MediaPlayer {
        private MediaAdpter mediaAdpter;

        @Override
        public void play(String audioType, String fileName) {
            //播放mp3
            if (audioType.equalsIgnoreCase("mp3")) {
                System.out.println("Playing mp3 file. Name: " + fileName);
            }
            //mediaAdapter 提供了播放其他文件格式的支持
            else if (audioType.equalsIgnoreCase("vlc")
                    || audioType.equalsIgnoreCase("mp4")) {
                mediaAdpter = new MediaAdpter(audioType);
                mediaAdpter.play(audioType, fileName);
            } else {
                System.out.println("Invalid media. " +
                        audioType + " format not supported");
            }
        }
    }

    //将一个类的接口转换成客户期望的另一个接口，适配器让原本接口不兼容的类可以相互合作。这个定义还好，说适配器的功能就是把一个接口转成另一个接口。
    public static void main(String[] args) {
        AudioPlayer audioPlayer = new AudioPlayer();
        audioPlayer.play("mp3", "1");
        audioPlayer.play("vlc", "2");
        audioPlayer.play("mp4", "3");
        audioPlayer.play("avi", "4");
    }
}
