package com.epam.events.tests;

import com.epam.events.steps.talks.TalksLibrarySteps;
import com.epam.events.utils.BaseConfigurationTest;
import org.testng.annotations.Test;

public class TalksLibrary extends BaseConfigurationTest {

    @Test
    public static void filteringReportsByCategory() {
        mainPage.open();
        navigateSection.clickTalksLibraryBtn();
        TalksLibrarySteps.addFilters("Design", "Belarus", "ENGLISH");
        TalksLibrarySteps.getAllUrlsCardsTalks();
        TalksLibrarySteps.assertOpenAllCardsTalksAndChecksData();
    }

    @Test
    public static void searchForReportsByKeyword() throws InterruptedException {
        mainPage.open();
        navigateSection.clickTalksLibraryBtn();
        TalksLibrarySteps.sendSearch("Azure");
        TalksLibrarySteps.getAllUrlsCardsTalks();
        TalksLibrarySteps.assertKeywordInTalks();
    }

}
