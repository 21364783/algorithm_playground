package com.algo.chap1;

/**
 * �����������ʵ��
 * ����ʱ��: 2011-3-28
 * @author Destiny
 *
 */
public class CoinMatrix
{
	private int rows;
	private int columns;
	private int count = 0;
	private int best = -1;
	private int[][] start;
	private int[][] end;
	private int[][] copyOfEnd;
	
	/**
	 */
	public CoinMatrix(int rows, int columns, int[][] start, int[][] end)
	{
		this.rows = rows;
		this.columns = columns;
		this.start = start;
		this.end = end;
		
		this.best = rows + columns + 1;
		
		this.copyOfEnd = new int[rows][columns];
	}
	
	public int getCount()
	{
		return count;
	}
	
	/**
	 * ������toBeCopied������copy������
	 * @param copy Ŀ������
	 * @param toBeCopied ��Ҫ������������
	 */
	private void createCopyOfEnd(int[][] copy, int[][] toBeCopied)
	{
		for(int i = 0; i < rows; i++)
			for(int j = 0; j < columns; j++)
				copy[i][j] = toBeCopied[i][j];
	}
	
	/**
	 * ������arr�еĵ�row���е�Ԫ��ȫ����ת�����磺��0��ת��1����1��ת��0
	 * @param arr ������ľ���
	 * @param row ���������
	 */
	private void transRows(int[][] arr, int row)
	{
		if(row >= this.rows)
		{
			System.out.println("Row Error!");
			return;
		}
		
		for(int i = 0; i < columns; i++)
			//�����е�ÿһ��Ԫ�ؽ����ֻ���
			arr[row][i] ^= 1;
		
		this.count++;
	}
	
	/**
	 * �������col1��col2�Ի�
	 * @param arr �������ľ���
	 * @param col1 ��������һ��
	 * @param col2 ����������һ��
	 */
	private void transColumns(int[][] arr, int col1, int col2)
	{
		if(col1 >= this.columns || col2 >= this.columns)
		{
			System.out.println("Column Error!");
			return;
		}
		
		//���col1��col2��ͬ����ʲôҲ����
		if(col1 == col2)
			return;
		
		int temp;
		for(int i = 0; i < this.rows; i++)
		{
			temp = arr[i][col1];
			arr[i][col1] = arr[i][col2];
			arr[i][col2] = temp;
		}
		
		this.count++;
	}
	
	/**
	 * �жϾ���arr1��col1�к;���arr2��col2���Ƿ���ȫһ��
	 * @param arr1 ���ж��ľ���1
	 * @param arr2 ���ж��ľ���2
	 * @param col1 ���ж��ľ���1�еĵ�col1��
	 * @param col2 ���ж��ľ���2�еĵ�col2��
	 * @return true���һ�£�false��һ��
	 */
	private boolean isColumnSame(int[][] arr1, int[][] arr2, int col1, int col2)
	{
		boolean flag = true;
		for(int i = 0; i < this.rows; i++)
		{
			if(arr1[i][col1] != arr2[i][col2])
				flag = false;
		}
		
		return flag;
	}
	
	/**
	 * ����ö�ٷ��ҵ�count��Ŀ��̵Ľⷨ
	 * @return �������裬��count��ֵ
	 */
	public int judgeFeasibility()
	{
		if(null == start || null == end)
			//����-2��ʾ�������
			return -2;
		
		boolean isFound;
		
		//��end״̬���󱣴�����
		this.createCopyOfEnd(copyOfEnd, end);
		
		//��ʼ����ѭ��ö��
		for(int i = 1; i < this.columns; i++)
		{
			//���ȶ�end������лָ�����
			this.createCopyOfEnd(end, copyOfEnd);
			//������������0
			this.count = 0;
			
			//����i�����һ�н���
			this.transColumns(end, 0, i);
			
			//����ѭ���Ƚϵ�һ����ÿ��Ԫ����start��һ����ÿ��Ԫ���Ƿ����
			//�������������б任
			for(int j = 0; j < rows; j++)
			{
				if(start[j][0] != end[j][0])
					this.transRows(end, j);
			}
			
			for(int j = 1; j < columns; j++)
			{
				//��ʼ����Ϊû���ҵ�
				isFound = false;
				for(int k = j; k < columns; k++)
				{
					//���start�еĵ�j�к�end�еĵ�k����ͬ
					if(this.isColumnSame(start, end, j, k))
					{
						this.transColumns(end, j, k);
						isFound = true;
						//�������forѭ��
						break;
					}
				}
				//������ѭ����û���ҵ���ͬ���У���ô��ֹ���ѭ��
				if(!isFound)
				{
					this.count = -1;
					break;
				}
			}
			
			//���������ֺ�best��ȣ�������
			if(this.count < this.best && count != -1)
				this.best = this.count;
		}
		
		return this.best;
	}
}
