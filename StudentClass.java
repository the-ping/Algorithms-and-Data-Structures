public class StudentClass {

	public KMPMatcher kmpMatcher;

	public StudentClass(String text, String pattern) {
		kmpMatcher = new KMPMatcher(text, pattern);
	}

	public void buildPrefixFunction() {
		kmpMatcher.setPrefixFunction(computePrefixFunction(kmpMatcher.getPattern()));
	}
	
	public static int[] computePrefixFunction(String pattern) {

		int m = pattern.length();
		int pi[] = new int[m]; //create an array pi, same length as pattern
		pi[0] = 0; //set first element of pi array to 0, 1 char doesnt have a match
		int k = 0;

		for (int q=1; q<m; q++ ) {
			while (k>0 && (pattern.charAt(k) != pattern.charAt(q))) {
				k = pi[k-1];
			} //if not equal elements, set pi value to 0
				if (pattern.charAt(k) == pattern.charAt(q)) {
					k = k + 1;
				}
				pi[q] = k; //setting pi value, corr. to qth element of pattern
			}
		return pi; //array pi returned
		}



	public static class KMPMatcher {

		private String text;
		private String pattern;
		private int textLen;
		private int patternLen;
		private int[] prefixFunction;
		private Queue matchIndices;

		public KMPMatcher(String text, String pattern) {
			this.text = text;
			this.pattern = pattern;
			this.textLen = text.length();
			this.patternLen = pattern.length();
			this.prefixFunction = new int[patternLen + 1];
			this.matchIndices = new Queue();
		}

		public void setPrefixFunction(int[] prefixFunction) {
			this.prefixFunction = prefixFunction;
		}

		public int[] getPrefixFunction() {
			return prefixFunction;
		}

		public String getPattern() {
			return pattern;
		}

		public Queue getMatchIndices() {
			return matchIndices;
		}

		public void search() {

			//pattern shouldnt be longer than text
			if (patternLen > textLen) {
				return;
			}

			int[] pi = getPrefixFunction(); //create pi array
			int q = 0;

			for (int i=0; i<textLen; i++) { //iterate through beginning (index 0) to end of text (index (n-1))
				while (q>0 && (pattern.charAt(q) != text.charAt(i))) { //first compare: P[i+1] against T[i]
					q = pi[q-1];
				}
				if (pattern.charAt(q) == text.charAt(i)) { //if match text with Pattern found
					q = q + 1;
				}
				if (q == (patternLen)) { //a full pattern is found in text

					//add to Q: index-1 , aka position just before the start of the pattern matched
					matchIndices.enqueue(i-(patternLen)+1); //+1 to @ 10:40pm
					q = pi[q-1];
				}
			}


		}
	}

}

