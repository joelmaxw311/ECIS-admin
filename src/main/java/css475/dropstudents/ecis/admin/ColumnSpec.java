/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package css475.dropstudents.ecis.admin;

import java.util.Objects;

/**
 *
 * @author joelm
 */
public class ColumnSpec {
    public enum ValueType {
        Text, Int, Real, Key
    }
    
    public final String name;
    public final ValueType type;
    public final String foreignTable;
    public final String foreignKey;
    public final ValueType foreignType;
    public final boolean isRequired;
    public final String foreignName; // key for foreign row "name" column
    
    public ColumnSpec(String name, ValueType type, boolean require) {
        if (type == ValueType.Key)
            throw new IllegalArgumentException(
                "ValueType.Key not allowed. Use ColumnSpec(name,ftable,fkey).");
        this.name = name;
        this.type = type;
        this.foreignTable = null;
        this.foreignKey = null;
        this.foreignType = null;
        this.isRequired = require;
        this.foreignName = null;
    }
    
    public ColumnSpec(String name, String ftable, String fkey, ValueType ftype, boolean require) {
        this(name, ftable, fkey, fkey, ftype, require);
    }
    
    public ColumnSpec(String name, String ftable, String fkey, String fname, ValueType ftype, boolean require) {
        
        if (ftype == ValueType.Key)
            throw new IllegalArgumentException(
                "ValueType.Key is not a valid foreign key type.");
        this.name = name;
        this.type = ValueType.Key;
        this.foreignTable = ftable;
        this.foreignKey = fkey;
        this.foreignName = fname;
        this.foreignType = ftype;
        this.isRequired = require;
    }
    
    public String getName() {
        return name;
    }
    
    public String formatValue(Object value) {
        if (value == null)
            return "NULL"; // sql NULL value
        switch (type) {
            case Key:
                return formatType(value, foreignType); // format with foreign key type
            default:
                return formatType(value, type); // format with column type
        }
    }
    
    private static String formatType(Object value, ValueType type) {
        switch (type) {
            case Text:
                return String.format("\"%s\"", value);
            case Int:
                return Integer.toString(Integer.parseInt(Objects.toString(value)));
            default: // Real, (Key)
                return Objects.toString(value);
        }
    }
}
