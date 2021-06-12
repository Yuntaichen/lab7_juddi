**Лабораторная работа 7. Регистрация и поиск сервиса в реестре jUDDI**

# Регистрация и поиск сервиса в реестре jUDDI

## Задание

Разработать приложение, осуществляющее регистрацию сервиса в реестре jUDDI, а также поиск сервиса в реестре и обращение к нему. 

Необходимо реализовать консольное приложение, которое обрабатывает 2 команды:

* Первая - регистрация сервиса в реестре.
* Вторая - поиск сервиса и обращение к нему.



## Ход работы

В данной работе необходимо скачать Apache jUDDI (реестр сервисов) по ссылке:

http://archive.apache.org/dist/juddi/juddi/3.2.0/

Таким образом мы получим последнюю версию jUDDI 3.3.9. Для Windows необходимо скачать zip-архив и распаковать его. Далее переходим в директории распакованного архива в каталог `juddi-tomcat-3.3.9/bin` и запускаем через файл `startup.bat`. Как правило, стоит запускать НЕ от имени администратора. При необходимости можно переименовать файл startup.bat или создать ярлык, а затем добавить его в переменную Path и производить запуск из консоли, например, по ключевому слову juddi. Необходимо иметь свободный порт 8080. Далее переходим по необходимому адресу:

* Для просмотра информации и администрирования - http://localhost:8080/juddiv3
* Для доступа к GUI - http://localhost:8080/juddi-gui

> Также имеется доступ по SSL через порт 8443 и сервер занимает IP 0.0.0.0, то есть должен быть доступен из всех "открытых" сетей. Но для доступа по SSL необходимо будет установить соответствующий сертификат.



![image-20210611092150976](README.assets/image-20210611092150976.png)

![image-20210611092204348](README.assets/image-20210611092204348.png)



В директории `\juddi-tomcat-3.3.9\conf` можно просмотреть пользователя по умолчанию в строках:

```xml
...
<role rolename="uddiadmin"/>
<user username="uddiadmin" password="da_password1" roles="uddiadmin,tomcat,manager" />
...
```

Следует их изменить следующим образом:

```xml
<tomcat-users>
    <role rolename="tomcat"/>
    <role rolename="manager"/>
    <role rolename="uddiadmin"/>
    <user username="uddiadmin" password="da_password1" roles="uddiadmin,tomcat,manager" />
</tomcat-users>
```

Далее перезапускаем jUDDI и используем указанные имя пользователя и пароль для входа в панель администрирования и gui.

 Попробуем зарегистрировать сервис через графический интерфейс в браузере, для чего изначально создаем во вкладке Create -> Business новый "бизнес" и далее во вкладке Create ->Register Services from WSDL создаем новый сервис, но для регистрации изначально его запустим - выбираем сервис из лабораторной работы 3, изменяем порт, например, на 8090, т. к. tomcat использует порт 8080 по умолчанию, и запускаем сервис. Далее производим его регистрацию. После успешной регистрации мы можем его найти и просмотреть через Discover -> Search.

![image-20210611124235683](README.assets/image-20210611124235683.png)

![image-20210611215409021](README.assets/image-20210611215409021.png)

Соответственно регистрируем сервис, который запустили на порту 8090 по адресу:

```http
http://localhost:8080/CRUDService?wsdl
```



![image-20210611215541028](README.assets/image-20210611215541028.png)

После чего, мы можем просмотреть наличие сервиса при помощи поиска.

![image-20210611124433110](README.assets/image-20210611124433110.png)

Далее приступаем к программной реализации клиента, причем изначально попытаем написать часть для поиска сервиса и обращения к нему. После этого приступим к реализации части с регистрацией сервиса в реестре.

В pom.xml добавляем зависимости:

```xml
	<dependencies>
		<dependency>
			<groupId>org.apache.juddi</groupId>
			<artifactId>uddi-ws</artifactId>
			<version>3.2.0</version>
		</dependency>
		<dependency>
			<groupId>org.apache.juddi</groupId>
			<artifactId>juddi-client</artifactId>
			<version>3.2.0</version>
		</dependency>
	</dependencies>
```

Для поиска сервиса будем следовать представленному в методическом пособии примеру "simple-browse" из каталога examples jUDDI. Создадим для поиска сервиса класс ServiceSearch, в котором объявим метод searchServiceByName, который будет возвращать указатель на сервис, а принимать в качестве аргумента имя сервиса для поиска.

Основные операции по получению токена будем производить из основного класса JUDDIApp, а затем их передавать в метод класса ServiceSearch в качестве параметров, причем будем передавать данные доступа inquiry и token, а также название сервиса, который необходимо найти. Для удобства и возможности последующего расширения функционала будем при этом создавать новый экземпляр класса ServiceSearch и вызывать метод searchAndGetService, т.е. метод будет не статическим, т. к. по итогу работы мы будем хранить состояние с полученным ключом и другими необходимыми данными для данного сервиса. Информацию мы можем выводить в также консоль, с вопросом о необходимости обращения к сервису.  В изначальной реализации мы будем получать список бизнесов в JUDDI и производить поиск сервисов их перебором и сравнением с именем сервиса в запросе, что и предполагает реализация клиента для поиска сервиса. С другой стороны, возможно использовать поиск JUDDI через api, что относительно будет легче. 

Для регистрации сервиса по аналогии с примером simple-publish-portable реализуем метод registerNewService(), который поместим в класс JUDDIApp. Данный метод будет получать токен, наименование бизнеса, который необходимо зарегистрировать изначально для дальнейшей регистрации сервиса, а также наименование сервиса и ссылку для получения WSDL. 

И последним шагом потребуется реализовать обращение к сервису, который мы получаем через поиск в консольном клиенте, а также возможность получения данных для поиска и регистрации сервисов через консоль. В данном случае нам необходимо результатом работы поиска сервиса получать ссылку на данный сервис, а затем при помощи данной ссылки и ранее разработанного клиента можно будет производить обращения непосредственно к сервису. Если же при поиске возвращается null, то значит, что сервис не был найден в реестре jUDDI и к нему нет возможности обратиться. 

 Необходимо переписать поиск сервиса через bindingTemplates, чтобы в результате выдавать accessPoint, т. е. URL для найденного сервиса.

В итоге, преобразуем класс ServiceSearch следующим образом:

```java

public class ServiceSearch {

    public String getAccessServicePoint(
            UDDIInquiryPortType inquiry,
            String token,
            String serviceName) {
        try {
            return searchService(GetBusinessList(inquiry, token).getBusinessInfos(), inquiry, token, serviceName);
        } catch (Exception ex) {
            Logger.getLogger(JUDDIApp.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * Find all of the registered businesses. This list may be filtered
     * based on access control rules
     */
    private BusinessList GetBusinessList(
            UDDIInquiryPortType inquiry,
            String token)
            throws Exception {
        FindBusiness fb = new FindBusiness();
        fb.setAuthInfo(token);
        org.uddi.api_v3.FindQualifiers fq = new org.uddi.api_v3.FindQualifiers();
        fq.getFindQualifier().add(UDDIConstants.APPROXIMATE_MATCH);
        fb.setFindQualifiers(fq);
        Name searchName = new Name();
        searchName.setValue(UDDIConstants.WILDCARD);
        fb.getName().add(searchName);

        return inquiry.findBusiness(fb);
    }

    private String searchService(
            BusinessInfos businessInfos,
            UDDIInquiryPortType inquiry,
            String token,
            String serviceName)
            throws Exception {

        for (int i = 0; i < businessInfos.getBusinessInfo().size(); i++) {
            GetServiceDetail gsd = new GetServiceDetail();
            // Перехватываем NullPointerException, чтобы не выводить в лог.
            // Данное исключение лишь показывает, что закончились сервисы и мы получили null
            try {
                for (int k = 0; k < businessInfos.getBusinessInfo().get(i).getServiceInfos().getServiceInfo().size(); k++) {
                    gsd.getServiceKey().add(businessInfos.getBusinessInfo().get(i).getServiceInfos().getServiceInfo().get(k).getServiceKey());
                }
                gsd.setAuthInfo(token);
                System.out.println("Fetching data for business " + businessInfos.getBusinessInfo().get(i).getBusinessKey());
                ServiceDetail serviceDetail = inquiry.getServiceDetail(gsd);
                for (int k = 0; k < serviceDetail.getBusinessService().size(); k++) {
                    BusinessService get = serviceDetail.getBusinessService().get(k);

                    if (ListToString(get.getName()).equals(serviceName)) {
                        return  getServiceAccessPoint(get.getBindingTemplates());
                    }
                }
            } catch (NullPointerException ex) {
                    System.out.println("That's it! We get a " + ex);
                    return null;
            }
        }
        return null;
    }

    /**
     * This function is useful for translating UDDI's somewhat complex data
     * format to something that is more useful.
     */
    private String getServiceAccessPoint(BindingTemplates bindingTemplates) {
        if (bindingTemplates == null) {
            return null;
        }
        String serviceAccessPoint = null;
        for (int i = 0; i < bindingTemplates.getBindingTemplate().size(); i++) {
            if (bindingTemplates.getBindingTemplate().get(i).getAccessPoint() != null) {
                if (bindingTemplates.getBindingTemplate().get(i).getAccessPoint().getUseType() != null) {
                    if (bindingTemplates.getBindingTemplate().get(i).getAccessPoint().getUseType().equalsIgnoreCase(AccessPointType.WSDL_DEPLOYMENT.toString())) {
                        serviceAccessPoint = bindingTemplates.getBindingTemplate().get(i).getAccessPoint().getValue();
                    }
                }
            }
        }
        return serviceAccessPoint;
    }

    private String ListToString(List<Name> name) {
        StringBuilder sb = new StringBuilder();
        for (Name value : name) {
            sb.append(value.getValue());
        }
        return sb.toString();
    }
}
```

Теперь мы попросту будем возвращать accessPoint в виде строки, например `http://localhost:8080/CRUDService?wsdl` и далее при его помощи обращаться к сервису.

Для обращения к сервису возьмем ранее разработанный код клиента (см. Лабораторная работа 3), со сгенерированным интерфейсом по WSDL-описанию. То есть для обращения к сервису, нам потребуется лишь передать accessPoint клиенту и вызвать необходимые методы.

