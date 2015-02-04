package com.algo.chap1;

import com.algo.gadgets.NumberHandler;

public class PageStatistics
{
	public static int[] pageCounter1(int numberOfPage)
	{
		int[] counts = new int[10];
		
		for (int i = 1; i <= numberOfPage; i++)
		{
			int temp = i;
			while(temp != 0)
			{
				counts[temp % 10]++;
				temp /= 10;
			}
		}
		return counts;
	}
	
	public static int[] pageCounter2(int numberOfPage)
	{
		int partial = numberOfPage;
		int length = (int)Math.log10(numberOfPage);
		int[] pow10 = new int[length + 1];
		pow10[0] = 1;
		int[] counts = new int[10];
		int[] numsArray = NumberHandler.convertIntToIntArray(numberOfPage);
		
		for (int i = 1; i < pow10.length; i++)
		{
			pow10[i] = pow10[0] * 10;
		}
		
		int index = 0;
		//���ｫlength��len�����б���һ�Σ�������ȥ����0�Ĵ�����������
		int len = length;
		int head = numsArray[index++];
		partial %= pow10[length];
		while(length > 0)
		{
			//����0�ĵ����������Ч�ʣ�
			if(head == 0)
			{
				counts[0] += partial + 1;
				head = numsArray[index++];
				length--;
				partial %= pow10[length];
				continue;
			}
			//�������λ������ֵĳ��ִ��������ݹ�ʽf(n)=n*10��n-1�η�
			for (int i = 0; i < 10; i++)
			{
				counts[i] += head * length * pow10[length - 1];
			}
			//������λ���ֵĳ��ִ����������ĳ��ִ�������������2234����ͳ�Ƶ���0xxx��1xxx��0��1�ĳ��ִ���
			for (int i = 0; i < head; i++)
			{
				counts[i] += pow10[length];
			}
			//Ϊʲô��partial + 1�أ���Ϊ��Ҫ����β������ȫ0�����
			counts[head] += partial + 1;
			length--;
			head = numsArray[index++];
			partial %= pow10[length];
		}
		
		//��ʼ�������ĸ�λ��
		for (int i = 0; i <= head; i++)
		{
			counts[i]++;
		}
		
		//�Զ����0���д�����Ϊ��ʽ�Ǹ���00000��00001������ʽ�����ֽ���ͳ�Ƶ�
		for (int i = 0; i < len; i++)
		{
			counts[0] -= pow10[len];
		}
		
		return counts;
	}
}
