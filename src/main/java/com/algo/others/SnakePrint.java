package com.algo.others;

public class SnakePrint
{
	private int row;
	private int column;
	private int[][] array;
	//��ʼ��������������ҵ�
	private Direction currentDirection = Direction.RIGHT;
	
	public SnakePrint(int row, int column)
	{
		this.row = row;
		this.column = column;
		array = new int[row][column];
	}
	
	//ע��ö�����͵�������ʽ!
	private enum Direction
	{
		LEFT, RIGHT, UP, DOWN;
	}
	
	/**
	 * ���ݵ�ǰ��λ�ú͵�ǰ�ķ������ж���һ������
	 * @param ri
	 * @param ci
	 * @return
	 */
	private Direction getNextDirection(int ri, int ci)
	{
		Direction direction = this.currentDirection;
		switch (direction)
		{
		case RIGHT:
			if(ci == this.column - 1 || array[ri][ci + 1] != 0)
				direction = Direction.DOWN;   // ����ʹ�� direction = direction.DOWN Ҳ�ǿ��Ե�
			break;
		case DOWN:
			if(ri == this.row - 1 || array[ri + 1][ci] != 0)
				direction = Direction.LEFT;
			break;
		case LEFT:
			if(ci == 0 || array[ri][ci - 1] != 0)
				direction = Direction.UP;
			break;
		case UP:
			if(ri == 0 || array[ri - 1][ci] != 0)
				direction = Direction.RIGHT;
			break;
		default:
			System.out.println("Something is wrong");
			break;
		}
		
		return direction;
	}
	
	/**
	 * ��ʼ������
	 */
	private void initArray()
	{
		int ri, ci;
		ri = ci = 0;
		
		for(int i = 0; i < this.row * this.column; i++)
		{
			array[ri][ci] = i + 1;
			currentDirection = this.getNextDirection(ri, ci);
			switch (currentDirection)
			{
			case RIGHT:
				ci++;
				break;
			case LEFT:
				ci--;
				break;
			case DOWN:
				ri++;
				break;
			case UP:
				ri--;
				break;
			default:
				System.out.println("Something is wrong!");
				break;
			}
		}
	}
	
	/**
	 * �������ӡ����
	 */
	public void printArray()
	{
		this.initArray();
		
		for(int i = 0; i < row; i++)
		{
			for (int j = 0; j < column; j++)
			{
				System.out.print(this.array[i][j] + "\t");
			}
			System.out.println();
		}
	}
}
