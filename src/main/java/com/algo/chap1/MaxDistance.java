package com.algo.chap1;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;

/**
 * 
 * ����ʱ��: 2011-3-29
 * @author Destiny
 *
 */
public class MaxDistance
{
	/**
	 * �������϶����ĵ�һ�ֽⷨ����Ҫ�ǽ�������֮����⣬���nlog(n)
	 */
	public static double firstMethod(String filePath)
	{
		DecimalFormat format = new DecimalFormat("#.0000");
		int n;
		try
		{
			FileReader fr =new FileReader(filePath);
			Scanner sc = new Scanner(fr);
			//���������ļ����ص���ж����������ȶ�ȡԪ�صĸ���
			if(sc.hasNextInt())
				n = sc.nextInt();
			else
				return -1;
			
			//����n�ĸ������½�һ��double���͵�����
			double x[] = new double[n];
			//ʹ��sc��ʣ�µ�Ԫ�ض��뵽x[]��
			for(int i = 0; i < n; i++)
				if(sc.hasNextDouble())
					x[i] = sc.nextDouble();
			
			//ʹ��Arrays����������ݽ�������
			//Ĭ��ʹ�ø�����QS�����Ӷ�nlog(n)
			Arrays.sort(x);
			//����max��ֵΪ�ڶ���Ԫ�ؼ�ȥ��һ��Ԫ�ص�ֵ
			double max = x[1] - x[0];
			//��ʼѭ��������⵽�����ֵ���򸳸�max
			for(int i = 2; i < n; i++)
				if(x[i] - x[i - 1] > max)
					max = x[i] - x[i - 1];
			
			return Double.parseDouble(format.format(max));
			
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
			return -1;
		} catch (Exception e) 
		{
			e.printStackTrace();
			return -1;
		}
	}
	
	public static double secondMethod(String filePath)
	{
		DecimalFormat format = new DecimalFormat("#.0000");
		int n;
		try
		{
			FileReader fr =new FileReader(filePath);
			Scanner sc = new Scanner(fr);
			//���������ļ����ص���ж����������ȶ�ȡԪ�صĸ���
			if(sc.hasNextInt())
				n = sc.nextInt();
			else
				return -1;
			
			//����n�ĸ������½�һ��double���͵�����
			double x[] = new double[n];
			//ʹ��sc��ʣ�µ�Ԫ�ض��뵽x[]��
			for(int i = 0; i < n; i++)
				if(sc.hasNextDouble())
					x[i] = sc.nextDouble();
			
			//��ȡ�����е����ֵ�Լ���Сֵ ���Ӷ�n
			double min = x[minIndex(n, x)];
			double max = x[maxIndex(n, x)];
			
			//���������������
			//���Ͱ�е�Ԫ�ظ�����ʵ��ʹ��1��n-1
			int[] count = new int[n];
			//��¼Ͱ���½磬ʵ��ʹ��1��n-1
			double[] low = new double[n];
			//��¼Ͱ���Ͻ磬ʵ��ʹ��1��n-1
			double[] high = new double[n];
			
			//�����ϵı��������г�ʼ��
			for(int i = 1; i < n; i++)
			{
				count[i] = 0;
				low[i] = max;
				high[i] = min;
			}
			
			//����һ��Ͱƽ����࣬���ݴ˾�����й���
			double averDis = Double.parseDouble(format.format((max - min) / (n - 1)));
			for(int i = 0; i < n; i++)
			{
				int bucket = (int)((x[i] - min) / averDis) + 1;
				if(n == bucket)
					bucket--;
				count[bucket]++;
				
				//��Ͱ���Ͻ���½���д���
				if(x[i] < low[bucket])
					low[bucket] = x[i];
				if(x[i] > high[bucket])
					high[bucket] = x[i];
			}
			
			//��ʼ��һ�������
			double maxDis = 0;
			//��������ķ������Ͱ1��Զ����Ԫ�صģ�ȥhigh[1]Ϊ�ұ߽�
			double leftBound = high[1];
			
			for(int i = 1; i < n; i++)
			{
				//ֻ��Ͱ�д���Ԫ�زŽ��д���
				if(count[i] > 0)
				{
					if(low[i] - leftBound > maxDis)
						maxDis = low[i] - leftBound;
					//�ı��ұ߽�Ϊ��ǰbucket��highֵ
					leftBound = high[i];
				}
			}
			
			return Double.parseDouble(format.format(maxDis));
			
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
			return -1;
		} catch (Exception e) 
		{
			e.printStackTrace();
			return -1;
		}
	}
	
	/**
	 * �õ�����x����СԪ�ص�index
	 * @param n ����ĳ���
	 * @param x �����ҵ�����
	 * @return ��СԪ�ص�����
	 */
	private static int minIndex(int n, double[] x)
	{
		double min = x[0];
		int index = 0;
		for(int i = 1; i < n; i++)
			if(x[i] < min)
			{
				min = x[i];
				index = i;
			}
		
		return index;
	}
	
	/**
	 * �õ�����x�����Ԫ�ص�index
	 * @param n ����ĳ���
	 * @param x �����ҵ�����
	 * @return ���Ԫ�ص�����
	 */
	private static int maxIndex(int n, double[] x)
	{
		double max = x[0];
		int index = 0;
		for(int i = 1; i < n; i++)
			if(x[i] > max)
			{
				max = x[i];
				index = i;
			}
		
		return index;
	}
}
