package oop.dictionary.model;


import com.voicerss.tts.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.FileOutputStream;
//import javax.sound.sampled.AudioInputStream;
//import javax.sound.sampled.AudioSystem;
//import javax.sound.sampled.Clip;

/**
 *
 * API text to speech.
 */
public class TtsApiController {
    private VoiceProvider tts;
    private FileOutputStream fos;
    private String APIKEY = "55190d23142741d9be41bcc701fb6a93";
    private static TtsApiController instance;
    
    private TtsApiController() {
        try {
            tts = new VoiceProvider(APIKEY);
            VoiceParameters us = new VoiceParameters("fake construct", Languages.English_UnitedStates);
            us.setVoice(Voices.English_UnitedStates.Mary);
            us.setCodec(AudioCodec.WAV);
            us.setFormat(AudioFormat.Format_44KHZ.AF_44khz_16bit_stereo);
            us.setBase64(false);
            us.setSSML(false);
            us.setRate(0);

            byte[] usvoice = tts.speech(us);
        }
        catch(Exception e) {
            
        }    
    } 
    
    
    public static TtsApiController getInstance() {
        if (instance == null) {
            instance = new TtsApiController();
        }
        return instance;
    }
    
    public void setUsSound(String word) {
        // us voice
        VoiceParameters us = new VoiceParameters(word, Languages.English_UnitedStates);
        us.setVoice(Voices.English_UnitedStates.Mary);
        us.setCodec(AudioCodec.WAV);
        us.setFormat(AudioFormat.Format_44KHZ.AF_44khz_16bit_stereo);
        us.setBase64(false);
        us.setSSML(false);
        us.setRate(0);
        
        try {
            byte[] usvoice = tts.speech(us);

            fos = new FileOutputStream("us.wav");
            fos.write(usvoice, 0, usvoice.length);
            fos.flush();
            fos.close();
        }
        catch (Exception e) {
            
        }
    }
    
    public void setUkSound(String word) {
        // uk voice
        VoiceParameters uk = new VoiceParameters(word, Languages.English_GreatBritain);
        uk.setVoice(Voices.English_GreatBritain.Nancy);
        uk.setCodec(AudioCodec.WAV);
        uk.setFormat(AudioFormat.Format_44KHZ.AF_44khz_16bit_stereo);
        uk.setBase64(false);
        uk.setSSML(false);
        uk.setRate(0);

        try {
            byte[] ukvoice = tts.speech(uk);

            fos = new FileOutputStream("uk.wav");
            fos.write(ukvoice, 0, ukvoice.length);
            fos.flush();
            fos.close();
        }
        catch (Exception e) {
            
        }
    }
    
    public void playUsSound(String word) {
        if (word != null) {
            try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("us.wav").getAbsoluteFile());
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            }
            catch(Exception e) {

            }
        }
    }

    public void playUkSound(String word) {
        if (word != null) {
            try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("uk.wav").getAbsoluteFile());
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            }
            catch(Exception e) {

            }
        }
    }
    
}
