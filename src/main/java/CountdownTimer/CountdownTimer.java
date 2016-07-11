package CountdownTimer;

/**
 * Timer für Babysteps
 *
 * @author Leonard
 */

public class CountdownTimer {

	long startTime = 0;
	long displayableSeconds = 0;
	long elapsedMinutes = 0;

	public void startCountdownTimer() {

		startTime = System.currentTimeMillis();

	}

	public void calculateElapsedTime() {

		long elapsedMillis = System.currentTimeMillis() - startTime;
		long elapsedSeconds = elapsedMillis/1000;
		elapsedMinutes = elapsedSeconds/60;
		displayableSeconds = elapsedSeconds%60;

	}

	public long getDisplayableSeconds() {

		return displayableSeconds;

	}

	public long getDisplayableMinutes() {

		return elapsedMinutes;

	}

}