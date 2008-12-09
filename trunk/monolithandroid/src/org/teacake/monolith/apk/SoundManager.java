package org.teacake.monolith.apk;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
public class SoundManager extends Thread
{
	SoundManager(android.content.Context context)
	{
		this.context = context;
		soundEvents = new java.util.LinkedList<Integer>();
		players = new java.util.HashMap<Integer, MediaPlayer>();
		isRunning = false;
	}
	public void addSound(int resid, boolean isLooping)
	{
		try
		{
			MediaPlayer mediaPlayer = MediaPlayer.create(context, resid);
			
			players.put(new Integer(resid), mediaPlayer);
			mediaPlayer.prepareAsync();
			mediaPlayer.setLooping(isLooping);
			
		}
		catch(Exception e)
		{
			
		}
	}
	
	public void run()
	{
	
		while(isRunning)
		{
			try
			{
				while(soundEvents.size()>0)
				{
				
					
					Integer resid = soundEvents.remove();
					
					if(resid!=null)
					{
						MediaPlayer mediaPlayer=players.get(resid);
						if(!mediaPlayer.isPlaying())
						{
							mediaPlayer.seekTo(0);
							mediaPlayer.start();
						}
						
					}
					
				}
			}
			catch(Exception e)
			{
				
			}

			try
			{
				java.lang.Thread.currentThread().sleep(100);
			}
			catch(Exception e)
			{
				
			}			
		}
	}


	public void startSound()
	{
		isRunning=true;
		this.start();
	}
	public void stopSound()
	{
		isRunning=false;
	}
	public void stopSound(int resid)
	{
		try
		{
			MediaPlayer mediaPlayer = players.get(new Integer(resid));
			mediaPlayer.stop();
		}
		catch(Exception e)
		{
			
		}
	}
	private boolean isRunning;
	private java.util.HashMap<Integer, MediaPlayer> players;
	private android.content.Context context;
	public static java.util.LinkedList<Integer > soundEvents;
	public static void playSound(int resid)
	{
		if(soundEvents!=null)
		{
			try
			{
				soundEvents.add(new Integer(resid));
			}
			catch(Exception e)
			{
				
			}
		}
	}
	
	
	
}