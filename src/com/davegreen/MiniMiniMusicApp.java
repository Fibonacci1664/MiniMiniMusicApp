package com.davegreen;

import javax.sound.midi.*;

public class MiniMiniMusicApp
{
    public void play()
    {
        try
        {
            Sequencer musicPlayer = MidiSystem.getSequencer();                      // Here we have to use the MidiSystem object of the sequencer Interface, remember that Sequencer
            musicPlayer.open();                                                     // is an Interface which inherently means you cannot instantiate it.
            
            Sequence sequence = new Sequence(Sequence.PPQ, 4);              // Here we create a new sequence object (think of a sequence like a CD that holds tracks)
            
            Track track = sequence.createTrack();                                   // Here we create a new Track object (Tracks hold all the midi information that the sequencer plays)
            
            ShortMessage first = new ShortMessage();
            first.setMessage(192, 1, 49, 0);            // This few lines of code is dedicated purely to changing the instrument that is played.
            MidiEvent changeInstrument = new MidiEvent(first, 1);               // A full list of instruments based on their 0 - 127 range can be found here:
            track.add(changeInstrument);                                            // https://www.midi.org/specifications/item/gm-level-1-sound-set
                                                                                    // Here is also some information related to the numerical midi commands (first argument):
                                                                                    // http://computermusicresource.com/MIDI.Commands.html
                                                                                    
            
            ShortMessage shortMessage1 = new ShortMessage();                        // In order to play a note we must create two things, a ShortMessage and a MidiEvent.
            shortMessage1.setMessage(144, 1, 80, 127);  // The ShortMessage holds exactly WHAT is going to be played, which note etc
            MidiEvent noteOn = new MidiEvent(shortMessage1, 1);                 // The MidiEvent holds the ShortMessage information but also declares WHEN this information (note)
            track.add(noteOn);                                                      // will be played, "tick 1" (beat 1).
                                                                                    // We then add the noteOn to the track ready to be played.
            ShortMessage shortMessage2 = new ShortMessage();
            shortMessage2.setMessage(128, 1, 80, 127);  // Here we are creating another note but this is the information pertaining to how the note will end
            MidiEvent noteOff = new MidiEvent(shortMessage2, 16);               // again we use a ShortMessage and a MidiEvent but the "tick" is when it will end "tick 16" (beat 16)
            track.add(noteOff);                                                     // Again we then have to add the noteOff information to the track in order that the track is aware of
                                                                                    // when to stop playing the note.
            musicPlayer.setSequence(sequence);                                      // Here we are adding the sequence to the musicPlayer (Sequencer).
            
            musicPlayer.start();                                                    // Here we are simply pushing the play button so to speak.
        }
        catch(Exception ex)
        {
            ex.printStackTrace();                                                   // This is obviously the catch for the exception.
        }
    
    
        //ShortMessage shortMessageExplained = new ShortMessage();
        
                                        //Message Type//Channel//Note to play//Velocity
        //shortMessageExplained.setMessage(144, 1, 44, 100);
        
        
        // Message Type/Command: 144 means NOTE ON (Start Playing)
        //               128 means NOTE OFF (Stop Playing)
        // Channel: Think of channels as musicians or instruments, different channels play different sounds, channel 1 may be a piano for example while channel 7 may be a drum sound.
        // Note to play/Data1: This is simply a range of numbers from 0 - 127 inclusive, ranging from low - high notes respectively, e.g. 4 = low note, 103 = high note.
        // Velocity/Data2: Think of this as, How fast and how hard is the note to be played, again in a range of numbers from 0 - 127 inclusive 0 = so soft you probably
        // wouldnt hear it, while 100 is a good default.
    }
}
