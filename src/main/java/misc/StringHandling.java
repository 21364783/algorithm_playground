package misc;

import org.junit.Test;

/**
 * ���ַ����еĴ�д��ĸ�ŵ��ַ����ĺ��棬ÿ���ַ������λ�ò��ܱ��ı�
 * �Ҳ��ܷ������ռ�
 * @author jiangr2
 *
 */
public class StringHandling {

	public static String handle(String str) {
		
		// ֻ��Ϊ�˴����һ�㣬�������ռ�
		// ����ϸ�Ҫ�󣬿���ʹ��str.charAt(index)����
		char[] chars = str.toCharArray();
		
		int upperIndex = 0;
		// �ҵ���һ����д��ĸ
		while(!Character.isUpperCase(chars[upperIndex])) {
			upperIndex++;
			
			// �ַ����в������κδ�д��ĸ
			if(chars.length == upperIndex) {
				return str;
			}
		}
		
		int lowerIndex = upperIndex + 1;
		
		// ��ֹ����
		while(lowerIndex != chars.length) {
			
			// ȷ��Сд��ĸ��index
			while(!Character.isLowerCase(chars[lowerIndex])) {
				lowerIndex++;
				
				// �ַ������Ѿ��������κ�Сд��ĸ
				if(chars.length == lowerIndex) {
					return new String(chars);
				}
			}
			
			// ���ڴ�upperIndex��lowerIndex֮��ȫ�����Ǵ�д��ĸ [upperIndex, lowerIndex)
			int middleIndex = upperIndex + 1;
			while(middleIndex != lowerIndex) {
				exch(chars, upperIndex, middleIndex);
				middleIndex++;
			}
			exch(chars, upperIndex, lowerIndex);
			upperIndex++;
			lowerIndex = upperIndex;
		}
		
		return new String(chars);
	}
	
	private static void exch(char[] src, int from, int to) {
		
		char t = src[from];
		src[from] = src[to];
		src[to] = t;
	}
	
	@Test
	public void testStrHandling() {
		String str = "aBcDeFgHiJkLmN";
		System.out.println(StringHandling.handle(str));
	}
}
