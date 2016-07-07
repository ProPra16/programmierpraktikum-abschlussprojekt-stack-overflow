package CountdownTimer;

public class CountdownTimer {

	long startTime = null;
	long displayableSeconds = null;
	long displayableMinutes = null;

	public static void startCountdownTimer() {

		startTime = System.currentTimeMillis();

	}

	public static void calculateElapsedTime() {

		long elapsedMillis = System.currentTimeMillis();
		long elapsedSeconds = elapsedTime/1000;
		long elapsedMinutes = elapsedSeconds/60;
		displayableSeconds = elapsedSeconds%60;
		displayableMinutes = elapsedMinutes%60;

	}

	public static long getDisplayableSeconds() {

		return displayableSeconds;

	}

	public static long getDisplayableMinutes() {

		return displayableMinutes;

	}

}