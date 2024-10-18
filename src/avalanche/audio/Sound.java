package avalanche.audio;

import static org.lwjgl.openal.AL10.*;
import static org.lwjgl.stb.STBVorbis.*;
import static org.lwjgl.system.MemoryStack.*;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import avalanche.engine.utils.Logger;
public class Sound {

    private int bufferID;
    private int sourceID; 
    private String filePath;

    private boolean isPlaying = false;

    public Sound(String filePath) {
    	File file = new File(filePath);
    	if (!file.exists()) {
    	    Logger.toConsole(Logger.logLevel.WARNING, "File does not exist: " + filePath);
    	    return;
    	}
    	
        this.filePath = filePath; 

        if (filePath.endsWith(".ogg")) {
            loadOgg(filePath);
        } else if (filePath.endsWith(".wav")) {
            loadWav(filePath);
        } else {
        	String extension = getFileExtension(filePath);
            Logger.toConsole(Logger.logLevel.WARNING, "Unsupported audio format (" + extension + "): " + filePath);
        }
    }
    
    private String getFileExtension(String filePath) {
        int lastIndex = filePath.lastIndexOf('.');
        if (lastIndex > 0 && lastIndex < filePath.length() - 1) {
            return filePath.substring(lastIndex + 1);
        }
        return ""; // No extension found
    }
    
    private void loadMp3(String filePath) {
    }

    private void loadWav(String filePath) {
        try {
            // Open the audio input stream
            File soundFile = new File(filePath);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            AudioFormat format = audioInputStream.getFormat();

            // Convert the audio input stream to byte array
            byte[] audioBytes = audioInputStream.readAllBytes();
            ByteBuffer audioBuffer = ByteBuffer.allocateDirect(audioBytes.length);
            audioBuffer.put(audioBytes).flip();

            // Determine the format for OpenAL
            int formatAL;
            if (format.getChannels() == 1) {
                formatAL = AL_FORMAT_MONO16;
            } else if (format.getChannels() == 2) {
                formatAL = AL_FORMAT_STEREO16;
            } else {
                Logger.toConsole(Logger.logLevel.WARNING, "Unsupported number of channels: " + format.getChannels());
                return;
            }

            // Create OpenAL buffer and source
            bufferID = alGenBuffers();
            alBufferData(bufferID, formatAL, audioBuffer, (int) format.getSampleRate());

            sourceID = alGenSources();
            alSourcei(sourceID, AL_BUFFER, bufferID);
            alSourcef(sourceID, AL_GAIN, 0.3f);

            audioInputStream.close();
        } catch (UnsupportedAudioFileException e) {
            Logger.toConsole(Logger.logLevel.WARNING, "Unsupported audio file format: " + filePath);
        } catch (IOException e) {
            Logger.toConsole(Logger.logLevel.WARNING, "Could not load sound: " + filePath);
        }
    }

    private void loadOgg(String filePath) {
        stackPush();
        IntBuffer channelsBuffer = stackMallocInt(1);
        IntBuffer sampleRateBuffer = stackMallocInt(1);

        // Decode the OGG file
        ShortBuffer rawAudioBuffer = stb_vorbis_decode_filename(filePath, channelsBuffer, sampleRateBuffer);

        if (rawAudioBuffer == null) {
            Logger.toConsole(Logger.logLevel.WARNING, "Could not load sound " + filePath);
            stackPop();
            return;
        }

        int channels = channelsBuffer.get();
        int sampleRate = sampleRateBuffer.get();
        stackPop();

        // Determine the format for OpenAL
        int format = (channels == 1) ? AL_FORMAT_MONO16 : AL_FORMAT_STEREO16;

        // Create OpenAL buffer and source
        bufferID = alGenBuffers();
        alBufferData(bufferID, format, rawAudioBuffer, sampleRate);

        sourceID = alGenSources();
        alSourcei(sourceID, AL_BUFFER, bufferID);
        alSourcef(sourceID, AL_GAIN, 0.3f);
    }


    public void delete() {
        alDeleteSources(sourceID);
        alDeleteBuffers(bufferID);
    }

    public void play() {
        int state = alGetSourcei(sourceID, AL_SOURCE_STATE);
        if (state == AL_STOPPED) {
            isPlaying = false;
            alSourcei(sourceID, AL_POSITION, 0);
        }

        if (!isPlaying) {
            alSourcePlay(sourceID);
            isPlaying = true;
        }
    }

    public void stop() {
        if (isPlaying) {
            alSourceStop(sourceID);
            isPlaying = false;
        }
    }

    public String getFilePath() {
        return filePath;
    }

    public boolean isPlaying() {
        int state = alGetSourcei(sourceID, AL_SOURCE_STATE);
        if (state == AL_STOPPED) {
            isPlaying = false;
        }

        return isPlaying;
    }
}