/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */

import org.apache.avro.specific.SpecificData;

@SuppressWarnings("all")
/** A pair of strings. */
@org.apache.avro.specific.AvroGenerated
public class StringPair extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 1923478547922011971L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"StringPair\",\"doc\":\"A pair of strings.\",\"fields\":[{\"name\":\"left\",\"type\":\"string\"},{\"name\":\"right\",\"type\":\"string\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  @Deprecated public java.lang.CharSequence left;
  @Deprecated public java.lang.CharSequence right;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public StringPair() {}

  /**
   * All-args constructor.
   * @param left The new value for left
   * @param right The new value for right
   */
  public StringPair(java.lang.CharSequence left, java.lang.CharSequence right) {
    this.left = left;
    this.right = right;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return left;
    case 1: return right;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: left = (java.lang.CharSequence)value$; break;
    case 1: right = (java.lang.CharSequence)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'left' field.
   * @return The value of the 'left' field.
   */
  public java.lang.CharSequence getLeft() {
    return left;
  }

  /**
   * Sets the value of the 'left' field.
   * @param value the value to set.
   */
  public void setLeft(java.lang.CharSequence value) {
    this.left = value;
  }

  /**
   * Gets the value of the 'right' field.
   * @return The value of the 'right' field.
   */
  public java.lang.CharSequence getRight() {
    return right;
  }

  /**
   * Sets the value of the 'right' field.
   * @param value the value to set.
   */
  public void setRight(java.lang.CharSequence value) {
    this.right = value;
  }

  /**
   * Creates a new StringPair RecordBuilder.
   * @return A new StringPair RecordBuilder
   */
  public static StringPair.Builder newBuilder() {
    return new StringPair.Builder();
  }

  /**
   * Creates a new StringPair RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new StringPair RecordBuilder
   */
  public static StringPair.Builder newBuilder(StringPair.Builder other) {
    return new StringPair.Builder(other);
  }

  /**
   * Creates a new StringPair RecordBuilder by copying an existing StringPair instance.
   * @param other The existing instance to copy.
   * @return A new StringPair RecordBuilder
   */
  public static StringPair.Builder newBuilder(StringPair other) {
    return new StringPair.Builder(other);
  }

  /**
   * RecordBuilder for StringPair instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<StringPair>
    implements org.apache.avro.data.RecordBuilder<StringPair> {

    private java.lang.CharSequence left;
    private java.lang.CharSequence right;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(StringPair.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.left)) {
        this.left = data().deepCopy(fields()[0].schema(), other.left);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.right)) {
        this.right = data().deepCopy(fields()[1].schema(), other.right);
        fieldSetFlags()[1] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing StringPair instance
     * @param other The existing instance to copy.
     */
    private Builder(StringPair other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.left)) {
        this.left = data().deepCopy(fields()[0].schema(), other.left);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.right)) {
        this.right = data().deepCopy(fields()[1].schema(), other.right);
        fieldSetFlags()[1] = true;
      }
    }

    /**
      * Gets the value of the 'left' field.
      * @return The value.
      */
    public java.lang.CharSequence getLeft() {
      return left;
    }

    /**
      * Sets the value of the 'left' field.
      * @param value The value of 'left'.
      * @return This builder.
      */
    public StringPair.Builder setLeft(java.lang.CharSequence value) {
      validate(fields()[0], value);
      this.left = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'left' field has been set.
      * @return True if the 'left' field has been set, false otherwise.
      */
    public boolean hasLeft() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'left' field.
      * @return This builder.
      */
    public StringPair.Builder clearLeft() {
      left = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'right' field.
      * @return The value.
      */
    public java.lang.CharSequence getRight() {
      return right;
    }

    /**
      * Sets the value of the 'right' field.
      * @param value The value of 'right'.
      * @return This builder.
      */
    public StringPair.Builder setRight(java.lang.CharSequence value) {
      validate(fields()[1], value);
      this.right = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'right' field has been set.
      * @return True if the 'right' field has been set, false otherwise.
      */
    public boolean hasRight() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'right' field.
      * @return This builder.
      */
    public StringPair.Builder clearRight() {
      right = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    @Override
    public StringPair build() {
      try {
        StringPair record = new StringPair();
        record.left = fieldSetFlags()[0] ? this.left : (java.lang.CharSequence) defaultValue(fields()[0]);
        record.right = fieldSetFlags()[1] ? this.right : (java.lang.CharSequence) defaultValue(fields()[1]);
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  private static final org.apache.avro.io.DatumWriter
    WRITER$ = new org.apache.avro.specific.SpecificDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  private static final org.apache.avro.io.DatumReader
    READER$ = new org.apache.avro.specific.SpecificDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}
