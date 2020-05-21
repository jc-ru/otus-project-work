package com.epam.events.tests;

import com.epam.events.steps.talks.TalksLibrarySteps;
import com.epam.events.utils.BaseConfigurationTest;
import org.testng.annotations.Test;

public class TalksLibrary extends BaseConfigurationTest {
    TalksLibrarySteps talksLibrarySteps = new TalksLibrarySteps();

    @Test
    void filteringReportsByCategory() {
        talksLibrarySteps.openTalksLibraryPage();
        talksLibrarySteps.addFilters("Design", "Belarus", "ENGLISH");
        talksLibrarySteps.getAllUrlsCardsTalks();
        talksLibrarySteps.assertOpenAllCardsTalksAndChecksData();
    }

    @Test
    void searchForReportsByKeyword() {
        talksLibrarySteps.openTalksLibraryPage();
        talksLibrarySteps.sendSearch("Azure");
        talksLibrarySteps.getAllUrlsCardsTalks();
        talksLibrarySteps.assertKeywordInTalks();
    }

}
