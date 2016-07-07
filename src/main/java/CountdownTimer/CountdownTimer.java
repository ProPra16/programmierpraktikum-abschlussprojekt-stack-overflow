package CountdownTimer;

public class CountdownTimer {

	long startTime = 0;
	long displayableSeconds = 0;
	long displayableMinutes = 0;

	public void startCountdownTimer() {

		startTime = System.currentTimeMillis();

	}

	public void calculateElapsedTime() {

		long elapsedMillis = System.currentTimeMillis();
		long elapsedSeconds = elapsedTime/1000;
		long elapsedMinutes = elapsedSeconds/60;
		displayableSeconds = elapsedSeconds%60;
		displayableMinutes = elapsedMinutes%60;

	}

	public long getDisplayableSeconds() {

		return displayableSeconds;

	}

	public long getDisplayableMinutes() {

		return displayableMinutes;

	}

}