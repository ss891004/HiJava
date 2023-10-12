
+ 用notepad++打开，在格式选择“以UTF-8无BOM格式编码”, xml 的编码格式。
+ UTF-8 BOM中的BOM，全称为Byte order mark。
  带BOM（签名）的UTF-8文本文件与不带BOM的UTF-8文本文件的区别在于：前者在文件的开头有3个字节 EF BB BF



+ JAXB(Java Architecture for XML Binding简称JAXB)

JAXB是一个业界的标准，是一项可以根据XML Schema产生Java类的技术。该过程中，JAXB也提供了将XML实例文档反向生成Java对象树的方法，并能将Java对象树的内容重新写到 XML实例文档。
Jaxb 2.0是JDK 1.6的组成部分。我们不需要下载第三方jar包 即可做到轻松转换。Jaxb2使用了JDK的新特性，如：Annotation、GenericType等，需要在即将转换的JavaBean中添加annotation注解。

重要的使用有:

JAXBContext类，是应用的入口，用于管理XML/Java绑定信息。
Marshaller接口，将Java对象序列化为XML数据。
Unmarshaller接口，将XML数据反序列化为Java对象。
@XmlType，将Java类或枚举类型映射到XML模式类型
@XmlAccessorType(XmlAccessType.FIELD) ，控制字段或属性的序列化。FIELD表示JAXB将自动绑定Java类中的每个非静态的（static）、非瞬态的（由@XmlTransient标 注）字段到XML。其他值还有XmlAccessType.PROPERTY和XmlAccessType.NONE。
@XmlAccessorOrder，控制JAXB 绑定类中属性和字段的排序。
@XmlJavaTypeAdapter，使用定制的适配器（即扩展抽象类XmlAdapter并覆盖marshal()和unmarshal()方法），以序列化Java类为XML。
@XmlElementWrapper ，对于数组或集合（即包含多个元素的成员变量），生成一个包装该数组或集合的XML元素（称为包装器）。
@XmlRootElement，将Java类或枚举类型映射到XML元素。
@XmlElement，将Java类的一个属性映射到与属性同名的一个XML元素。
@XmlAttribute，将Java类的一个属性映射到与属性同名的一个XML属性。


```xml
<?xml version="1.0" encoding="UTF-8"?>
<c c1="0">
    <d d1="101280101" d2="重庆" d3="nanping" d4="南坪"/>
    <d d1="101280102" d2="重庆" d3="yubei" d4="渝北"/>
    <d d1="101280103" d2="重庆" d3="dadukou" d4="大渡口"/>
</c>

```

```java
import lombok.Data;
 
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
 
 
@Data
//根元素
@XmlRootElement(name = "d")
//访问类型，通过字段
@XmlAccessorType(XmlAccessType.FIELD)
public class City {
 
    @XmlAttribute(name = "d1")
    private String cityId;
    @XmlAttribute(name = "d2")
    private String cityName;
    @XmlAttribute(name = "d3")
    private String cityCode;
    @XmlAttribute(name = "d4")
    private String area;
 
}
```

```java
import lombok.Data;

        import javax.xml.bind.annotation.XmlAccessType;
        import javax.xml.bind.annotation.XmlAccessorType;
        import javax.xml.bind.annotation.XmlElement;
        import javax.xml.bind.annotation.XmlRootElement;
        import java.util.List;


@Data
@XmlRootElement(name = "c")
@XmlAccessorType(XmlAccessType.FIELD)
public class CityList {
    @XmlElement(name = "d")
    private List<City> cityList;
}
```

```java
 
import com.thoughtworks.xstream.XStream;
 
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.Reader;
import java.io.StringReader;
 
 
public class XmlBuilder {
 
    /**
     * JAXB将XML转为指定的POJO
     *
     * @param clazz
     * @param xml
     * @return
     */
    public static Object JAXB_XmlToBean(Class<?> clazz, String xml) {
        try {
            Object xmlObject;
            Reader reader;
            JAXBContext context = JAXBContext.newInstance(clazz);
            // XML 转为对象的接口
            Unmarshaller unmarshaller = context.createUnmarshaller();
            reader = new StringReader(xml);
            //以文件流的方式传入这个string
            xmlObject = unmarshaller.unmarshal(reader);
            reader.close();
            return xmlObject;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
 
    /**
     * XStream将XML转为指定的POJO
     *
     * @param clazz
     * @param xml
     * @return
     */
    public static Object XStream_ToBean(Class<?> clazz, String xml) {
        Object xmlObject;
        XStream xstream = new XStream();
        xstream.processAnnotations(clazz);
        xstream.autodetectAnnotations(true);
        xmlObject = xstream.fromXML(xml);
        return xmlObject;
    }
}

```

+ Java对象转XML字符串
```java
public static String convertToXml(Object obj) {
		// 创建输出流
		StringWriter sw = new StringWriter();
		try {
			// 利用jdk中自带的转换类实现
			JAXBContext context = JAXBContext.newInstance(obj.getClass());

			Marshaller marshaller = context.createMarshaller();
			// 格式化xml输出格式，并去除 <?xml version="1.0" encoding="UTF-8" standalone="yes"?>
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
			// 格式化xml输出的格式
			//marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			// 将对象转换成输出流形式的xml
			marshaller.marshal(obj, sw);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return sw.toString();
	}

```
+ 将String类型的xml转换成Java对象
```java
public static Object convertXmlStrToObject(Class clazz, String xmlStr) {
		Object xmlObject = null;
		try {
			JAXBContext context = JAXBContext.newInstance(clazz);
			// 进行将Xml转成对象的核心接口
			Unmarshaller unmarshaller = context.createUnmarshaller();
			StringReader sr = new StringReader(xmlStr);
			xmlObject = unmarshaller.unmarshal(sr);
		} catch (JAXBException e) {
			logger.info("--convertXmlStrToObject-- Class={} , xmlStr={},异常 \n {}",clazz.getClass(),xmlStr,e);
//			e.printStackTrace();
		}
		return xmlObject;
	}

```

+ 将对象根据路径转换成xml文件
```java
/**
	 * 将对象根据路径转换成xml文件
	 * 
	 * @param obj
	 * @param path
	 * @return
	 */
	public static void convertToXml(Object obj, String path) {
		try {
			// 利用jdk中自带的转换类实现
			JAXBContext context = JAXBContext.newInstance(obj.getClass());

			Marshaller marshaller = context.createMarshaller();
			// 格式化xml输出的格式
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			// 将对象转换成输出流形式的xml
			// 创建输出流
			FileWriter fw = null;
			try {
				fw = new FileWriter(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
			marshaller.marshal(obj, fw);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

```

+ 将file类型的xml转换成对象
```java
/**
	 * 将file类型的xml转换成对象
	 */
	public static Object convertXmlFileToObject(@SuppressWarnings("rawtypes") Class clazz, String xmlPath) {
		Object xmlObject = null;
		try {
			JAXBContext context = JAXBContext.newInstance(clazz);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			FileReader fr = null;
			try {
				fr = new FileReader(xmlPath);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			xmlObject = unmarshaller.unmarshal(fr);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return xmlObject;
	}
}

```