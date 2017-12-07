package SQLCrafters;

public class DeleteStatementCrafter {

    public String craftTableContentsDelete(String tableToDelete){
        return "DELETE FROM " + tableToDelete+";";
    }
}
