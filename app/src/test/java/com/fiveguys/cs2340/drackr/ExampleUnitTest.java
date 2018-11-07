package com.fiveguys.cs2340.drackr;

import org.junit.Test;

import static org.junit.Assert.*;

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


    @Test
    public void testSignInWithNullArgs() {
        UserAccount signedInUserBefore = UserAuthenticator.getSignedInUserAccount();
        Boolean a = UserAuthenticator.signInWith(null, null);
        UserAccount signedInUserAfter = UserAuthenticator.getSignedInUserAccount();
        assertFalse(a);
        assertEquals(signedInUserBefore, signedInUserAfter);
    }

    @Test
    public void testSignInWithEmptyArgs() {
        UserAccount signedInUserBefore = UserAuthenticator.getSignedInUserAccount();
        Boolean a = UserAuthenticator.signInWith("", "");
        UserAccount signedInUserAfter = UserAuthenticator.getSignedInUserAccount();
        assertFalse(a);
        assertEquals(signedInUserBefore, signedInUserAfter);
    }

    @Test
    public void testSignInWithValidArgs() {
        UserAccount a = new UserAccount("j", "jj", "jjj");
        Boolean b = UserAuthenticator.signInWith("jj", "jjj");
        UserAccount signedInUserAfter = UserAuthenticator.getSignedInUserAccount();
        assertTrue(b);
        assertEquals(a, signedInUserAfter);
    }

}