class MultiStreamReaderGenerator
!!!163269.java!!!	MultiStreamReaderGenerator(inout streams : Collection<? extends InputStream>)
        SequenceInputStream combinedReaderStream =
                new SequenceInputStream(Collections.enumeration(streams));
        ByteArrayOutputStream byteos = new ByteArrayOutputStream();
        IOUtils.copy(combinedReaderStream, byteos);
        combinedByteArray = byteos.toByteArray();
!!!163397.java!!!	generate() : Reader
        return new BufferedReader(
                new InputStreamReader(
                        new ByteArrayInputStream(combinedByteArray)));
