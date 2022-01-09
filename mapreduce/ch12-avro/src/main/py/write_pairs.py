import os
import string
import sys

import avro.schema
from avro.io import DatumWriter
from avro.datafile import DataFileWriter

if __name__ == '__main__':
    if len(sys.argv) != 2:
        sys.exit('Usage: %s <data_file>' % sys.argv[0])
    avro_file = sys.argv[1]
    writer = open(avro_file, 'wb')
    datum_writer = DatumWriter()
    schema_object = avro.schema.parse('\
    {   "type": "record", \
        "name": "StringPair",\
        "doc": "A pair of strings.",\
        "fields": [\
            {"name": "left", "type": "string"},\
            {"name": "right", "type":"string"}\
        ]\
    }')
    dfw = DataFileWriter(writer, datum_writer, schema_object)
    for line in sys.stdin.readlines():
        (left, right) = line.strip().split(',')
        dfw.append({'left':left, 'right':right});
    dfw.close()