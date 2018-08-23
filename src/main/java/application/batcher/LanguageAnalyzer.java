package application.batcher;

import java.io.InputStream;
import java.util.logging.Logger;

import com.ibm.watson.developer_cloud.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalysisResults;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalyzeOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.Features;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.SentimentOptions;

public class LanguageAnalyzer {

    private final static Logger logger = Logger.getLogger("sample");

    NaturalLanguageUnderstanding service = new NaturalLanguageUnderstanding(WatsonKeys.version, WatsonKeys.username,
            WatsonKeys.password);
    InputStream input = null;

    // ranges from -1 (negative sentiment) to 1 (positive sentiment)
    public SentimentObject analyze(String text) {

        SentimentOptions sentiment = new SentimentOptions.Builder().build();

        Features features = new Features.Builder().sentiment(sentiment).build();

        AnalyzeOptions parameters = new AnalyzeOptions.Builder().text(text).features(features).build();

        AnalysisResults response = service.analyze(parameters).execute();

        String label = response.getSentiment().getDocument().getLabel();
        Double score = response.getSentiment().getDocument().getScore();
        return new SentimentObject(label, score);
    }
}

