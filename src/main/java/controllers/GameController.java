package controllers;

import models.*;
import models.fields.Field;
import models.fields.Property;
import ui.GUIController;

public class GameController {
    private DiceHolder diceHolder = new DiceHolder(1);
    private int turnCounter = 0;
    private Language language;
    private GUIController guiController;
    private PlayerController playerController;
    private FieldController fieldController;
    public GameController(){
        language = new Language(System.getProperty("user.language"));

        fieldController = new FieldController(language);
        guiController = new GUIController(fieldController.getFieldList());
        int playerAmount = guiController.playerAmount();
        playerController = new PlayerController(playerAmount);
        String name;
        String originalName = "car, racecar, ufo, tractor";
        StringBuilder sb = new StringBuilder("Car,Tractor,Racecar,UFO");

        for (int i = 0; i < playerAmount; i++) {

            name = guiController.getName("inputName");
            while(!playerController.playerUnique(name)){
                guiController.displayMsg("nameNotUnique");
                name = guiController.getName("inputName");
            }

            String character = guiController.selectCharacter("selectCharacter", String.valueOf(sb));
            sb.delete(sb.indexOf(character),sb.indexOf(character)+character.length()+1);

            playerController.addPlayer(i, character, name);
        }
        guiController.setPlayers(playerController.getPlayers());
        while(true){
            Player player = playerController.getPlayerById(turnCounter);
            TakeTurn(player);
        }

    }
    private void EndGame(){

    }

    public void TakeTurn(Player player){
        diceHolder.roll();
        guiController.displayDice(diceHolder.getRolls());
        if(player.getLocation() + diceHolder.sum() >= 24){
            guiController.displayMsg(language.getLanguageValue("passStart"));
        }
        playerController.playerMove(player, diceHolder.sum());
        guiController.updatePlayer(player);
        Field field = fieldController.getField(player.getLocation());
        //Choose logic based on the field type
        switch (field.getClass().getSimpleName()){
            case "Property": {
                Property property = (Property) field;
                if(property.getOwner() == null && player.decreaseSoldSign()){
                    guiController.displayMsg("fieldBuy");
                    if(player.setBalance(-property.getPrice())){
                        fieldController.setOwner(player, property.getID());
                        guiController.updateField(property);
                        guiController.updatePlayer(player);
                    }else{
                        EndGame();
                    }
                } else if (player.getSoldSign()<0) {
                    guiController.displayMsg("You can't buy this since you've run out of houses");
                } else{
                    guiController.displayMsg("fieldRent");
                    if(!playerController.getRent(player, property)){
                        EndGame();
                    }else{
                        guiController.updatePlayer(property.getOwner());
                    }
                }
                break;
            }
            case "Chance":{
                guiController.displayMsg(language.getLanguageValue("fieldChance"));
                break;
            }
            case "Start": {
                guiController.displayMsg(language.getLanguageValue("fieldLandStart"));
                break;
            }
            case "Empty":{
                guiController.displayMsg(language.getLanguageValue("fieldFreeParking"));
                break;
            }
            case "ToJail": {
                guiController.displayMsg("fieldGoToJail");
                fieldController.jailPlayer(player);
                guiController.updatePlayer(player);
            }
            default: {
                break;
            }
        }
        turnCounter++;
    }

}
