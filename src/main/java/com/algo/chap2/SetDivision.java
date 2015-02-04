package com.algo.chap2;

import com.algo.gadgets.NumberHandler;

/**
 * ʵ���Ͼ�����Bell��������
 * ���ƹ�ʽΪ��
 *	B(0) = 1,
 *	B(n+1) = Sum(0,n) C(n,k)B(k). n = 1,2,...
 * ���У�Sum(0,n)��ʾ��k��0��n��ͣ�C(n,k) = n!/[k!(n-k)!]
 * ʹ�ñ���¼���������������б���
 * 
 * ����Stirling����
 * S(n,n) = S(n,1) = 1,
 * S(n,k) = S(n-1,k-1) + kS(n-1,k)
 * 
 * Bell����Stirling��֮��Ĺ�ϵ��
 * B(n) = Sum(1,n) S(n,k)
 * 
 * ����ʱ��: 2011-4-7
 * @author Destiny
 *
 */
public class SetDivision
{
	//�������Bell����nֵ
	private int n;
	
	//������ֵ
	private long result;
	
	//����׳˵Ľ���������ظ�����
	private long[] factorialResults;
	
	//������ϵĽ���������ظ����㣬�˾�����һ�������Ǿ�������Ŀռ俪���Ǹ����Ⱑ��
	private long[][] combinationResults;
	
	public SetDivision(int n)
	{
		this.n = n;
		this.factorialResults = new long[n];
		this.combinationResults = new long[n][n];
		
		//Ŀǰֻ��ʼ����factorialResult����
		this.init();
	}
	
	private void init()
	{
		for(int i = 0; i < n; i++)
			this.factorialResults[i] = NumberHandler.factorial(i);
	}

	/**
	 * �Ա�����n�е����������㣬������������result��
	 * �ⲿ���ô˷������м��㣬ʹ��getResult�õ����
	 */
	public void caculateBellNumber()
	{
		this.result = this.execute(n);
	}
	
	/**
	 * �ݹ�Ľ������
	 * @param n ��ǰֵ
	 * @return ���ص�ǰֵ��bell��
	 */
	private long execute(int n)
	{
		if(n == 0)
			return 1;
		
		long sum = 0;
		long com;
		for(int i = 0; i < n; i++)
		{
			//˵���˽��֮ǰ�����
			if(this.combinationResults[n - 1][i] > 0)
				com = this.combinationResults[n - 1][i];
			else
				com = this.caculateCombination(n - 1, i);
			
			//δ������������࣬�����ȼ���factorialResult
			sum += com * this.execute(i);
		}
		
		return sum;
	}
	
	private long caculateCombination(int n, int k)
	{
		if(n < k)
		{
			System.out.println("Wrong!");
			return -1;
		}
		
		long numerator = this.factorialResults[n];
		long denominator = this.factorialResults[k] * this.factorialResults[n - k];
		
		long result = numerator / denominator;
		this.combinationResults[n][k] = result;
		
		return result;
	}

	//�ı�n��ֵʱ���Խ������Ҳ��Ҫ�����޸�
	public void setN(int n)
	{
		//��������n����Ŀǰ��nֵ���Խ�������������
		if(n > this.n)
		{
			this.factorialResults = new long[n];
			this.combinationResults = new long[n][n];
			
//			System.arraycopy(factorialResults, 0, newFactorialResults, 0, factorialResults.length);
//			System.arraycopy(combinationResults, 0, newCombinationResults, 0, combinationResults.length);
//			
//			this.factorialResults = newFactorialResults;
//			this.combinationResults = newCombinationResults;
			
			this.n = n;
		}
	}

	/**
	 * ͨ��m��n����˹������
	 * @param m ����Ԫ�ظ���
	 * @param n ���ּ��ϸ���
	 * @return Stirling��
	 */
	public long caculateStirling(int m, int n)
	{
		if((m < n) || m <= 0 || n <= 0)
		{
			System.out.println("Wrong!");
			return -1;
		}
		
		if((m == n) || (n == 1))
			return 1;
		
		return this.caculateStirling(m - 1, n - 1) + n * this.caculateStirling(m - 1, n);
	}
	
	public long getResult()
	{
		return result;
	}

	public long[] getFactorialResults()
	{
		return factorialResults;
	}

	public long[][] getCombinationResults()
	{
		return combinationResults;
	}
}
