package com.algo.chap3;

/**
 * �����������ŵ�������Ľⷨ
 * 
 * �����k����ҵ�������A������xʱ�䣬����B������ʱ�����Сֵ�϶���x��һ������
 * ��F[k][x]��ʾ����B������ʱ�����Сֵ����F[k][x]=Min{ F[k-1][x]+b[k], F[k-1][x-a[k]] }
 * ����F[k-1][x]+b[k]��ʾ��k����ҵ�ɻ���B���������k-1����ҵʱ����A���ѵ�ʱ������x��
 * F[k-1][x-a[k]]��ʾ��k����ҵ�ɻ���A�������k-1����ҵʱ����A���ѵ�ʱ����x-a[k]��
 * 
 * ����ʱ��: 2011-4-12
 * @author Destiny
 *
 */
public class IndependentJob
{
	/**
	 * ������ҵ����Ŀ
	 */
	private int numOfJobs;
	
	/**
	 * �ֱ𱣴�ÿ��Jobʹ��A����Bʱ�ĺ�ʱ��Ϣ
	 */
	private int[] aTimes;
	private int[] bTimes;
	
	/**
	 * ������ҵ����1-n�ǵ�����ʱ�䣬time[n - 1]����ʾ���Ľ��
	 */
	private int[] times;
	
	/**
	 * ������ҵ1-n�Ǳ���һ�����
	 * ����results[0]='A' ����ʾ��ҵ1����A��ɵ�
	 */
	private String[] results;
	
	public IndependentJob(int num, int[] a, int[] b)
	{
		this.numOfJobs = num;
		this.aTimes = a;
		this.bTimes = b;
		
		//�Բ���������г�ʼ��
		this.times = new int[num];
		this.results = new String[num];
	}
	
	/**
	 * 
	 */
	public void process()
	{
		//����Ϊ���Ľ������һ�������ֵ
		for(int i = 0; i < this.times.length; i++)
			this.times[i] = 999;
		
		//���ȶԴ����A��B���з���
		int sumA, sumB;
		sumA = sumB = 0;
		for (int i = 0; i < this.aTimes.length; i++)
		{
			sumA += aTimes[i];
			sumB += bTimes[i];
		}
		int sum = 1 + ((sumA > sumB) ? sumB : sumA);
		
		/**
		 * ����A�����õ�ʱ��
		 */
		int[][] timeA = new int[this.numOfJobs][sum];
		
		/**
		 * ����B�����õ�ʱ��
		 */
		int[][] timeB = new int[this.numOfJobs][sum];
		
		/**
		 * ��¼���߹����ʱ��
		 */
		int[][] timeMax = new int[this.numOfJobs][sum];
		
		/**
		 * ��¼������һ�����
		 */
		char[][] who = new char[this.numOfJobs][sum];
		
		/**
		 * ����������ʱ�Ľ����
		 */
		char[] tempResult = new char[this.numOfJobs];
		
		//��ʼ�����һ����������ֵ����¼�������ĸ���ά����ĵ�0��
		//ʼ����A����Ҫ��ʱΪ����
		for(int i = 0; i <= this.aTimes[0]; i++)
		{
			//������Σ�timeA�м�¼�ľ���ѭ��������ֵ
			timeA[0][i] = i;
			if(i < aTimes[0])	//��ζ��A������ɣ�ʱ�䲻������Ҫ��B�����
			{
				timeB[0][i] = this.bTimes[0];
				who[0][i] = 'B';
			}
			else	//	��ζ��A����ɣ�ʱ��պõ��ڵ�һ������A����ɵ�ʱ�䣬��aTimes[0]
			{
				timeB[0][i] = 0;
				who[0][i] = 'A';
			}
			//����timeMax����Ӧ��Ԫ��
			timeMax[0][i] = Math.max(timeA[0][i], timeB[0][i]);
		}
		
		//����result
		if(aTimes[0] < bTimes[0])
		{
			this.times[0] = aTimes[0];
			tempResult[0] = 'A';
		}
		else
		{
			this.times[0] = bTimes[0];
			tempResult[0] = 'B';
		}
		this.results[0] = new String(tempResult);
		
		//��ʼ�����2�����񵽵�n�������ʱ����Ϣ
		for(int k = 1; k < this.numOfJobs; k++)
		{
			 //tempSum��¼���ǰk���������A�����Ҫ��ʱ��
			 //��ȫ����A�����Ҫ��ʱ�䣬�༴����A���п��ܵ�ȡֵ��Χ
			int tempSum = 0;
			for(int j = 0; j <= k; j++)
				tempSum += aTimes[j];
			
			//��������п��ܵĵ��(timeA, timeB)����ȡֵtimeMax
			for(int i = 0; i < tempSum; i++)
			{
				//������Σ�timeA�м�¼�ľ���ѭ��������ֵ
				timeA[k][i] = i;
				
				//��ζ��ֻ����B����ɣ�����aTimes[k]�ǵ�k���������A����Ҫ��ʱ��
				if(i < aTimes[k])
				{
					timeB[k][i] = timeB[k - 1][i] + bTimes[k];
					who[k][i] = 'B';
				}
				else
				{
					//��ζ����B���������ʱ�����
					if((timeB[k - 1][i] + bTimes[k]) <= timeB[k - 1][i - aTimes[k]])
					{
						timeB[k][i] = timeB[k - 1][i] + bTimes[k];
						who[k][i] = 'B';
					}
					//��ζ����A��������Ҫ��ʱ�����
					else
					{
						timeB[k][i] = timeB[k - 1][i - aTimes[k]];
						who[k][i] = 'A';
					}
				}
				//A��B�л���ʱ��ϴ���Ǹ�Ϊ�ܻ���ʱ��
				timeMax[k][i] = Math.max(timeA[k][i], timeB[k][i]);
			}
				
			//��������tempSum�����ֵ������Aʱ��ȫ����Ϊ��󣬴�ʱ����B�����軨��ʱ�䡣
            for (int i = tempSum + 1; i < sumA; i++)
            {
                timeA[k][i] = tempSum;
                timeB[k][i] = 0;
            }
            
            //��ɵ�k���������timeMax���п���ֵ�У�ѡȡ��Сֵ������ֵ��
            int flag = 0;//��¼����ֵ���ڵ�λ��iֵ��ͬʱҲ�ǻ���A�����ѵ�ʱ�䡣
            for (int i = 0; i <= tempSum; i++)
            {
                if (timeMax[k][i] > 0 && timeMax[k][i] < times[k])
                {
                	times[k] = timeMax[k][i];
                    flag = i;
                }
            }
            
            //------------------------------------------���µ����ݲ�̫��
          //�����������û�����˳����Ҫע����ǻ���A�����ѵ�ʱ���������±�һ������˿�������ǡ�
            //���һ������Ļ���·������ʾ��ͼ�б����������⡣
            //http://hi.baidu.com/liongg/blog/item/63d6a9ec19454c2262d09f01.html
            tempResult[k] = who[k][flag];
            for (int i = k; i > 0 && flag > 0; i--)
            {
                //����ǻ���A��ɵ�i�����������A���ѵ�ʱ���ȥa[i]�������ǰi-1�������ʱ�䡣
                //ͬʱ��who�п���ȷ��ǰһ������Ļ�������Ϊ����A���ѵ�ʱ�������λ�ñ�ǡ�
                if (tempResult[i] == 'A')
                {
                    flag -= aTimes[i];
                    tempResult[i - 1] = who[i - 1][flag];
                }
                if (tempResult[i] == 'B')
                {
                	tempResult[i - 1] = who[i - 1][flag];
                }
            }
            results[k] = new String(tempResult);
		}
	}

	public int[] getTimes()
	{
		return times;
	}

	public String[] getResults()
	{
		return results;
	}
}
