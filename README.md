# GuitarAmp_4th_Year_Main_Project
Fourth Year Main Project. A Java Guitar Amplifier with built-in effects to process the sound of the guitar.

## Libraries Used.
This project uses only the  **[Apache Commons Math 3.6.1](http://commons.apache.org/proper/commons-math/download_math.cgi)** library. This library is used to access the `FastFourierTransform` and `Complex` classes in order to re-use already existing algorithms and speed up the process of development.

```
FastFourierTransformer transformer = new FastFourierTransformer(DftNormalization.STANDARD);
Complex[] fourier_transform = transformer.transform(padding, TransformType.FORWARD);
```

## Application GUI Screenshot
![alt-tag](https://cloud.githubusercontent.com/assets/26202076/25042791/9c394fa0-2112-11e7-9326-acceca25dca7.png)
