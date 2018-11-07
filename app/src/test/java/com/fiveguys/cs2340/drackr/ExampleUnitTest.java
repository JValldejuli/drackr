package com.fiveguys.cs2340.drackr;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import java.util.Date;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testRegisterNullArgs() {
        UserAccount signedInUserBefore = UserAuthenticator.getSignedInUserAccount();
        UserAuthenticator.registerUserAccount(null, null, null);
        UserAccount signedInUserAfter = UserAuthenticator.getSignedInUserAccount();
        assertEquals(signedInUserBefore, signedInUserAfter);
    }

    @Test
    public void testRegisterEmptyArgs() {
        UserAccount signedInUserBefore = UserAuthenticator.getSignedInUserAccount();
        UserAuthenticator.registerUserAccount("", "", "");
        UserAccount signedInUserAfter = UserAuthenticator.getSignedInUserAccount();
        assertEquals(signedInUserBefore, signedInUserAfter);
    }

    @Test
    public void testRegisterValidArgs() {
        UserAccount a = new UserAccount("s", "d", "f");
        UserAuthenticator.registerUserAccount("s", "d", "f");
        UserAccount signedInUserAfter = UserAuthenticator.getSignedInUserAccount();
        assertEquals(signedInUserAfter.getName(), a.getName());
        assertEquals(signedInUserAfter.getEmail(), a.getEmail());
        assertEquals(signedInUserAfter.getPassword(), a.getPassword());
    }

    private Charity makeCharity() {
        List<Donation> a = new ArrayList<Donation>();
        a.add(new Donation(new Date(), "2", "shirt", 0, DonationType.CLOTHING));
        a.add(new Donation(new Date(), "2", "computer", 0, DonationType.ELECTRONICS));
        return new Charity("", "", 1, 1, "", "", "", 0, CharityType.DROP_OFF, "", "", a);
    }

    @Test
    public void testSetSpecificCharity() {
        Charity c = makeCharity();
        DonationSearchCoordinator.setSpecificCharity(c);
        assertEquals(c, DonationSearchCoordinator.getSpecificCharity());
    }

    @Test
    public void testSpecificCharityDonationTypeSearch() {
        Charity c = makeCharity();
        DonationSearchCoordinator.setSpecificCharity(c);
        DonationSearchCoordinator.searchDonationsByType(DonationType.CLOTHING);
        Donation result = DonationSearchCoordinator.getResults().get(0);
        assertEquals(result, c.getDonations().get(0));
    }

    @Test
    public void testSpecificCharityDescriptionSearch() {
        Charity c = makeCharity();
        DonationSearchCoordinator.setSpecificCharity(c);
        DonationSearchCoordinator.searchDonationsByDescription("computer");
        Donation result = DonationSearchCoordinator.getResults().get(0);
        assertEquals(result, c.getDonations().get(1));
    }

}