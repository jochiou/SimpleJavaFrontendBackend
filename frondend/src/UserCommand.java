import java.io.*;
import java.net.Socket;
import java.util.List;

/**
 * Created by josephchiou on 6/15/17.
 */
public class UserCommand extends DataObject implements Serializable {
    private String userID;
    private String commandType;
    private String commandTarget;
    private String targetTable;
    private String conditionTarget;
    private String whereOperator;
    private String whereValue;

    private String columnNameSets; //for insert: 1~ many column seperate by comma, for update: 1 args

    private String valueSets;   //for insert: 1~ many column seperate by comma, for update: 1 args

    //select
    // userid, select, * , (from) someTable, (where)conditionTarget, <=, someValue,
    public UserCommand(String userID, String commandType, String commandTarget, String targetTable, String conditionTarget, String whereOperator, String whereValue ){
        this.userID = userID;
        this.commandType = "select";
        this.commandTarget = commandTarget;
        this.targetTable = targetTable;
        this.conditionTarget = conditionTarget;
        this.whereOperator = whereOperator;
        this.whereValue = whereValue;
    }

    //insert
    //  insert into tableName ( column1, column2,...) values (value1, value2, ...)
    public UserCommand(String userID, String commandType, String targetTable, String columnNameSets, String valueSets ){
        this.userID = userID;
        this.commandType = "insert";
        this.targetTable = targetTable;
        this.columnNameSets = columnNameSets;
        this.valueSets = valueSets;
    }
    //update and delete
    public UserCommand(String userID, String commandType, String targetTable, String columnNameSets, String valueSets, String conditionTarget, String whereOperator, String whereValue ){
        this.userID = userID;
        this.commandType = commandType;
        this.targetTable = targetTable;
        this.columnNameSets = columnNameSets;
        this.valueSets = valueSets;
        this.whereOperator = whereOperator;
        this.whereValue = whereValue;
    }


    public String getCommandType() {
        return commandType;
    }

    public String getCommandTarget() {
        return commandTarget;
    }

    public String getTargetTable() {
        return targetTable;
    }

    public String getConditionTarget() {
        return conditionTarget;
    }

    public String getWhereOperator() {
        return whereOperator;
    }

    public String getWhereValue() {
        return whereValue;
    }

    public String getUserID() {
        return userID;
    }

    public String getColumnNameSets() {
        return columnNameSets;
    }

    public String getValueSets() {
        return valueSets;
    }



}

