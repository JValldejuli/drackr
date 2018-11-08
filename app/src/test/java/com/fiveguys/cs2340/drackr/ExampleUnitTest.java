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
    public void testSpecificCharityDonationTypeSearch() {
        Charity c = makeCharity();
        DonationSearchCoordinator.shared.setSpecificCharity(c);
        DonationSearchCoordinator.shared.searchDonationsByType(DonationType.CLOTHING);
        Donation result = DonationSearchCoordinator.shared.getResults().get(0);
        assertEquals(result, c.getDonations().get(0));
    }

    @Test
    public void testSpecificCharityDescriptionSearch() {
        Charity c = makeCharity();
        DonationSearchCoordinator.shared.setSpecificCharity(c);
        DonationSearchCoordinator.shared.searchDonationsByDescription("computer");
        Donation result = DonationSearchCoordinator.shared.getResults().get(0);
        assertEquals(result, c.getDonations().get(1));
    }
    @Test
    public void testRegisterNullArgs() {
        UserAccount signedInUserBefore = UserAuthenticator.shared.getSignedInUserAccount();
        UserAuthenticator.shared.registerUserAccount(null, null, null);
        UserAccount signedInUserAfter = UserAuthenticator.shared.getSignedInUserAccount();
        assertEquals(signedInUserBefore, signedInUserAfter);
    }

    @Test
    public void testSetSpecificCharity() {
        Charity c = makeCharity();
        DonationSearchCoordinator.shared.setSpecificCharity(c);
        assertEquals(c, DonationSearchCoordinator.shared.getSpecificCharity());
    }

    @Test
    public void testSignInWithValidArgs() {
        UserAccount a = new UserAccount("j", "jj", "jjj");
        UserAuthenticator.shared.registerUserAccount("j", "jj", "jjj");
        Boolean b = UserAuthenticator.shared.signInWith("jj", "jjj");
        UserAccount signedInUserAfter = UserAuthenticator.shared.getSignedInUserAccount();
        assertTrue(b);
        assertEquals(a.getName(), signedInUserAfter.getName());
        assertEquals(a.getEmail(), signedInUserAfter.getEmail());
        assertEquals(a.getPassword(), signedInUserAfter.getPassword());
    }

    @Test
    public void testRegisterValidArgs() {
        UserAccount a = new UserAccount("s", "d", "f");
        UserAuthenticator.shared.registerUserAccount("s", "d", "f");
        UserAccount signedInUserAfter = UserAuthenticator.shared.getSignedInUserAccount();
        assertEquals(signedInUserAfter.getName(), a.getName());
        assertEquals(signedInUserAfter.getEmail(), a.getEmail());
        assertEquals(signedInUserAfter.getPassword(), a.getPassword());
    }

    @Test
    public void testSignInWithValidArgsSB() {
        UserAccount a = new UserAccount("spongebob", "sb@gmail.com", "shellcity");
        UserAuthenticator.shared.registerUserAccount("spongebob", "sb@gmail.com", "shellcity");
        Boolean b = UserAuthenticator.shared.signInWith("sb@gmail.com", "shellcity");
        UserAccount signedInUserAfter = UserAuthenticator.shared.getSignedInUserAccount();
        assertTrue(b);
        assertEquals(a.getName(), signedInUserAfter.getName());
        assertEquals(a.getEmail(), signedInUserAfter.getEmail());
        assertEquals(a.getPassword(), signedInUserAfter.getPassword());
    }

    @Test
    public void testRegisterEmptyArgs() {
        UserAccount signedInUserBefore = UserAuthenticator.shared.getSignedInUserAccount();
        UserAuthenticator.shared.registerUserAccount("", "", "");
        UserAccount signedInUserAfter = UserAuthenticator.shared.getSignedInUserAccount();
        assertEquals(signedInUserBefore, signedInUserAfter);
    }


    @Test
    public void testSignInWithNullArgs() {
        UserAccount signedInUserBefore = UserAuthenticator.shared.getSignedInUserAccount();
        Boolean a = UserAuthenticator.shared.signInWith(null, null);
        UserAccount signedInUserAfter = UserAuthenticator.shared.getSignedInUserAccount();
        assertFalse(a);
        assertEquals(signedInUserBefore, signedInUserAfter);
    }

    @Test
    public void testSignInWithEmptyArgs() {
        UserAccount signedInUserBefore = UserAuthenticator.shared.getSignedInUserAccount();
        Boolean a = UserAuthenticator.shared.signInWith("", "");
        UserAccount signedInUserAfter = UserAuthenticator.shared.getSignedInUserAccount();
        assertFalse(a);
        assertEquals(signedInUserBefore, signedInUserAfter);
    }


    private Charity makeCharity() {
        List<Donation> a = new ArrayList<>();
        a.add(new Donation(new Date(), "2", "shirt", 0, DonationType.CLOTHING));
        a.add(new Donation(new Date(), "2", "computer", 0, DonationType.ELECTRONICS));
        return new Charity("", "", 1, 1, "", "", "", 0, CharityType.DROP_OFF, "", "", a);
    }




}