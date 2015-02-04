package chap2;

public class ShellSort<T> implements Sortable<T>
{
	private static int[] steps = {1, 3, 7, 12, 19};
	
	public static <T> void sort(Comparable<T>[] a)
	{
		int N = a.length;
		int h = 1;
		
		while(h < N / 3)
		{
			h = h * 3 + 1;
		}
		
		//ʵ���ϣ�ֻ���ҵ�h��min�ŵ�ǰ�漸��index������
		//���ǽ����Ȼ�����룬shell��Ȼ�Ǻ���ֵ�һ�����򷽷�
//		for(int i = 0; i < h; i++)
//		{
//			int min_index = i;
//			for(int j = i + 1; j < a.length; j++)
//			{
//				if(SortUtils.less(a[j], a[min_index]))
//				{
//					min_index = j;
//				}
//			}
//			SortUtils.exch(a, i, min_index);
//		}
		
		while(h >= 1)
		{
			for(int i = h; i < N; i++)
			{
				for(int j = i; j >= h && SortUtils.less(a[j], a[j - h]); j -= h)
				{
					//�����иĽ��Ŀռ䣬Ǳ�ڵĽ����������ܱȽ϶�
					SortUtils.exch(a, j, j - h);
				}
				
				// ���Ľ�����㷨��װ���÷�����
//				InsertionWithSentinelSelection.sortWithSentinelStep(a, h, i);
			}
			
			h /= 3;
		}
	}
	
	public static <T> void sortByStepsFromArray(Comparable<T>[] a)
	{
		int step_index = 0;
		
		// Compute the step_index
		int N = a.length;
		
		while(steps[step_index] < N / 3)
		{
			step_index++;
		}
		
		while(step_index != -1)
		{
			for(int i = steps[step_index]; i < N; i++)
			{
				for(int j = i; j >= steps[step_index] && SortUtils.less(a[j], a[j - steps[step_index]]); j -= steps[step_index])
				{
					SortUtils.exch(a, j, j - steps[step_index]);
				}
			}
			
			step_index--;
		}
	}

	@Override
	public void sortIt(Comparable<T>[] a)
	{
		ShellSort.sort(a);
	}
}
