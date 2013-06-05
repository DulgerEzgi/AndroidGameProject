  public void startTimer()
	{
		if (secondsPassed == 0) 
		{
			timer.removeCallbacks(updateTimeElasped);
			// timer 1 saniyeden sonra aktifleşecek
			timer.postDelayed(updateTimeElasped, 1000);
		}
	}

	public void stopTimer()
	{
		
		timer.removeCallbacks(updateTimeElasped);
	}

	
	private Runnable updateTimeElasped = new Runnable()
	{
		public void run()
		{
			long currentMilliseconds = System.currentTimeMillis();
			++secondsPassed;

			if (secondsPassed < 10)
			{
				txtTimer.setText("00" + Integer.toString(secondsPassed));
			}
			else if (secondsPassed < 100)
			{
				txtTimer.setText("0" + Integer.toString(secondsPassed));
			}
			else
			{
				txtTimer.setText(Integer.toString(secondsPassed));
			}
 
			
			timer.postAtTime(this, currentMilliseconds);
			
			timer.postDelayed(updateTimeElasped, 1000);
		}
	};
	
	private void showDialog(String message, int milliseconds, boolean useSmileImage, boolean useCoolImage)
	{
		// mesajı göster
		Toast dialog = Toast.makeText(
				getApplicationContext(),
				message,
				Toast.LENGTH_LONG);

		dialog.setGravity(Gravity.CENTER, 0, 0);
		LinearLayout dialogView = (LinearLayout) dialog.getView();
		ImageView coolImage = new ImageView(getApplicationContext());
		if (useSmileImage)
		{
			coolImage.setImageResource(R.drawable.smiley_17);
		}
		else if (useCoolImage)
		{
			coolImage.setImageResource(R.drawable.papatya);
		}
		else
		{
			coolImage.setImageResource(R.drawable.sad1);
		}
		dialogView.addView(coolImage, 0);
		dialog.setDuration(milliseconds);
		dialog.show();
	}
}
