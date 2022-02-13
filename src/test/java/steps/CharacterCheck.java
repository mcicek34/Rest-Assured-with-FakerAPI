package steps;

import cucumber.api.java.en.Then;

public class CharacterCheck {

    @Then("^Character fields are checked for character length \"([^\"]*)\"$")
    public void characterFieldsAreCheckedForCharacterLength(int characterLength){
        GetMethods.characterFieldsAreChecked(characterLength);
    }
}
