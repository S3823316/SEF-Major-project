Task #3.4: TESTING NOTES AND INSTRUCTIONS (ALSO INCLUDED IN THE ACCEPTANCE TESTING DOCUMENT)

To run tests, there are two options:
I recommend right clicking the java test directory (the green one) by going to src>test>java and selecting "Run All Tests"
It is also possible to click the maven tab on the right hand side of intellij and then m2>Lifecycle>test right click run
Either works but the former is more specific to which tests failed and which passed.

There should be some tests that fail at the current moment due to mainly security, this is intended and are intended
to have future updates in the code to fix these.

Writing tests cases for two major functions these are:

admin functions including: deleting users, approving shows,
disabling and approving users and shows, etc.

standard user functions including: logging in and searching for things like viewing shows, viewing the production
companies, reviews etc.


For reference for what I've mentioned below, here is the official javalin documentation for testing:
https://javalin.io/tutorials/testing

The tests are performed with the following java testing features in mind.
Javalin recommends using mockito to properly mock classes so they do not affect the database when entering or deleting
entries, as such this is what I've used alongside Junit.


I spent a lot of time trying to figure out how to test the controllers but I never got even one to pass, so I
ended up testing the services instead. I'm not really sure if this counts, but the controllers basically call these
methods anyway and all of the controller tests would essentially be stubs too, that is return an expected value.


The context objects from javalin are basically unlike any of the examples given because they always have an object
inside those classes to verify them whereas there are none in our controllers. Instead there is simply an input context
parameter which then creates a service class instance and passes it to search, view, get, etc., sometimes the context
isn't even used. I know it's just testing the database but this seems the only thing that's possible, it does at
least test for if the database will handle or output which is just an added step from the controller anyway, it's also
able to check for sqlinjection.


As for the notation, I looked at the notation used in the examples and lab exercises provided but most of it is actually
deprecated so I didn't use it because of that reason. I guess it hasn't been updated recently but no problem, we had
more examples in the lectures. The examples used are also very simple compared to what needs to be done for a web
application so a lot of it I had already done and was not particularly helpful. Granted it may have been better or
easier using a different language but seeing as Java was the prime example used in class I don't think it's very helpful
or acceptable.


I tried my best to test both common and borderline as well as security so hopefully that's enough.

Morgan Compton
Role: Tester
