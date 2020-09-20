import javax.sound.midi.*;

public class MusicTest1
{
	public void play()
	{
		// Sequencer sequencer = MidiSystem.getSequencer();
		// System.out.println("We got a sequencer");
		
		try		//有风险的语句块用try
		{
			Sequencer sequencer = MidiSystem.getSequencer();
			System.out.println("We got a sequencer");
		}catch(MidiUnavailableException ex)	//处理异常的语句块
		{
			System.out.println("Bummer");
			ex.printStackTrace();
		}

	}
	
	public static void main(String[] args)
	{
		MusicTest1 mt = new MusicTest1();
		mt.play();                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             
	}
}