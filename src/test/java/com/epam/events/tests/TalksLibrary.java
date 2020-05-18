package com.epam.events.tests;

import com.epam.events.utils.BaseConfigurationTest;
import org.testng.annotations.Test;

import static com.epam.events.steps.talks.TalksLibrarySteps.*;

public class TalksLibrary extends BaseConfigurationTest {

    @Test
    public static void filteringReportsByCategory() {
        openTalksLibraryPage();
        addFilters("Design", "Belarus", "ENGLISH");
        getAllUrlsCardsTalks();
        assertOpenAllCardsTalksAndChecksData();
    }

    @Test
    public static void searchForReportsByKeyword() {
        openTalksLibraryPage();
        sendSearch("Azure");
        getAllUrlsCardsTalks();
        assertKeywordInTalks();
    }

}
