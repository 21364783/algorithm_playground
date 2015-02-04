package com.algo.chap3;

/**
 * 0-1 ��������
 * �ݹ���ʽ��
 * m(i, j) = max{m(i+1, j), m(i+1, j-w[i]) + v[i]} when j >= w[i]
 * m(i, j) = m(i+1, j) when j < w[i]
 * ����ʱ��: 2011-4-11
 * @author Destiny
 *
 */
public class KnapsackZeroOne
{
	private int[] weight;
	private int[] value;
	private int num;
	private int maxWeight;
	
	/**
	 * wΪ�����±����㣬��matrix���ó�[num+1][maxWeight+1]�ľ���
	 */
	private int[][] matrix;
	
	/**
	 * ���ڱ�������ȡ������1����ȡ��0����ȡ
	 */
	private int[] result;
	
	/**
	 * �����ܼ�ֵ
	 */
	private int totalValue;
	
	public KnapsackZeroOne(int num, int maxWeight, int[] weight, int[] value) throws Exception
	{
		if(num != weight.length || num != value.length)
			throw new Exception("��Ʒ�ĸ�����weight�����Ԫ�ظ������value�����Ԫ�ظ�����һ�£�");
		
		//���г�ʼ���Ĳ���
		this.weight = weight;
		this.value = value;
		this.num = num;
		this.maxWeight = maxWeight;
		
		//��ʼ��matrix�Լ�result
		matrix = new int[num + 1][maxWeight + 1];
		result = new int[num + 1];
		
		//��matrix���и�ֵ���������ݶ�̬�滮ԭ��
		this.initMatrix();
	}
	
	private void initMatrix()
	{
		//�����һ��Ԫ�ؿ�ʼ��ʼ����ע��weight��value�����Ԫ���±�
		
		//�����һ��Ԫ�ؽ��д���ע��matrix[i][j]������ǣ���������j������£�����Ʒi��num�ܹ��õ�������ֵ
		for(int i = weight[this.num - 1]; i <= maxWeight; i++)
			matrix[this.num][i] = this.value[num - 1];
		
		//�Ե�2��Ԫ�ص���n - 1��Ԫ�ؽ��д���
		for(int i = this.num - 1; i > 1; i--)
		{
			//ȡ�����������һ����Ʒ����������Сֵ
			int bound = Math.min(weight[i - 1] - 1, this.maxWeight);
			for (int j = 0; j <= bound; j++)
				this.matrix[i][j] = this.matrix[i + 1][j];
			for(int j = weight[i - 1]; j <= this.maxWeight; j++)
				this.matrix[i][j] = Math.max(this.matrix[i + 1][j], this.matrix[i + 1][j - weight[i - 1]] + value[i - 1]);
		}
		
		//�����1-num������ʹ�2-num�������ͬ
		matrix[1][maxWeight] = matrix[2][maxWeight];
		if(maxWeight >= this.weight[0])
			matrix[1][maxWeight] = Math.max(matrix[2][maxWeight], matrix[2][maxWeight - weight[0]] + value[0]);
	}
	
	public void constructResults()
	{
		int maxW = maxWeight;
		//��1��n-1����Ʒ�����ж�
		for(int i = 1; i < num; i++)
		{
			if(matrix[i][maxW] == matrix[i + 1][maxW])
				//���i��i + 1�ľ���ֵ��ȣ���ô��û����Ҳ������ν��
				result[i] = 0;
			else
			{
				result[i] = 1;
				this.totalValue += value[i - 1];
				maxW -= weight[i - 1];
			}
		}
		//�����һ����Ʒ�����ж�
		result[num] = (matrix[num][maxW] >= 0) ? 1 : 0;
		if(result[num] == 1)
			this.totalValue += value[num - 1];
	}

	public int[] getResult()
	{
		return result;
	}

	public int getValue()
	{
		return totalValue;
	}
}
