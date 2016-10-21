package com.yuvalhermelin;

import java.util.Arrays;

public class PeakCounter {
	public static void main(String[] args) {
		CSVData data = CSVData.readCSVFile("/Users/yuvalhermelin/Downloads/GyroTest2out.csv", 0);
		System.out.println(Arrays.toString(data.getTitles()));
		double[] gyro3D = new double[data.numberOfRows()];
		for (int i = 1; i < data.numberOfRows(); i++) {
			gyro3D[i] = Math.sqrt((data.getRow(i)[1] * data.getRow(i)[1]) + (data.getRow(i)[2] * data.getRow(i)[2])
					+ (data.getRow(i)[3] * data.getRow(i)[3]));
		}

		double threshold = 0;
		double mean = 0;
		for (int i = 0; i < gyro3D.length; i++)
			mean += gyro3D[i];
		mean /= gyro3D.length;
		for (int i = 0; i < gyro3D.length; i++)
			threshold += (gyro3D[i] - mean) * (gyro3D[i] - mean);
		threshold /= gyro3D.length - 1;
		threshold = Math.sqrt(threshold);

		System.out.println(threshold);

		int peaks = 0;

		for (int i = 1; i < gyro3D.length - 1; i++)
			if (gyro3D[i] > gyro3D[i - 1] && gyro3D[i] > gyro3D[i + 1])
				if (gyro3D[i] > threshold)
					peaks++;
		System.out.println(peaks);
	}
}
