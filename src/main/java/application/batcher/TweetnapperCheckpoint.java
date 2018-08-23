package application.batcher;

import java.io.Serializable;

public class TweetnapperCheckpoint implements Serializable {
	private static final long serialVersionUID = 1L;
	private long lineNum = 0;
    public void increase() { lineNum++; }
    public long getLineNum() { return lineNum; }
}