package chap2;

public class InsertionWithSentinel<T> implements Sortable<T>
{
	public static <T> void sortWithSentinel(Comparable<T>[] a)
	{
		int N = a.length;
		
		// ��Sentinel�ŵ�indexΪ0�ĵط�������һ��bubble���̣�����С�ķŵ���ͷ��λ��
		// ���Ըù��̾���bubble_sort��ȱ�㣬��Ǳ�ڵ�exchange����̫�࣬ʹ��selection�Ľ�
		for(int i = N - 1; i > 0; i--)
		{
			if(SortUtils.less(a[i], a[i - 1]))
			{
				SortUtils.exch(a, i, i - 1);
			}
		}
		
		for(int i = 2; i < N; i++)
		{
			Comparable<T> v = a[i];
			int j = i;
			
			while(SortUtils.less(v, a[j - 1]))
			{
				a[j] = a[j - 1];
				j--;
			}
			
			a[j] = v;
		}
	}

	@Override
	public void sortIt(Comparable<T>[] a)
	{
		InsertionWithSentinel.sortWithSentinel(a);
	}
}
