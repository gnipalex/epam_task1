package com.epam.hnyp.task6.subtask3;

public class Test {
	public static void main(String[] args) {
		SequenceInfo longestSequence = new SequenceInfo();
		byte[] data = new byte[] {1, 2, 3, 3, 2, 4, 2, 1, 1, 1, 5, 1, 1};
		for (int i=0; i<data.length; i++) {
			byte item = data[i];
			int offset = i;
			int len = 1;
			for (int j=i; j<data.length; j++) {
				if (j + 1 < data.length && data[j + 1] == item) {
					len++;
					continue;
				}
				break;
			}
			if (len > 1) {
				if (len > longestSequence.length) {
					longestSequence.item = item;
					longestSequence.length = len;
					longestSequence.offset = offset;
				}
				i += len - 1;
			}
		}
		System.out.println(longestSequence);
	}
	
	static class SequenceInfo {
		private int offset;
		private int length;
		private byte item;

		public int getOffset() {
			return offset;
		}

		public void setOffset(int offset) {
			this.offset = offset;
		}

		public int getLength() {
			return length;
		}

		public void setLength(int length) {
			this.length = length;
		}

		public byte getItem() {
			return item;
		}

		public void setItem(byte item) {
			this.item = item;
		}
		
		@Override
		public String toString() {
			return "item = " + item + ", offset = " + offset + ", len = " + length;
		}
	}
}
