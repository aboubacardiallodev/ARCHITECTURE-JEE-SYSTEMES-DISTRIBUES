package net.aboubacar.framework.context;

import net.aboubacar.framework.annotations.Autowired;
import net.aboubacar.framework.annotations.Component;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Conteneur IoC basé sur les annotations (similaire à AnnotationConfigApplicationContext de Spring)
 * Scanne le package donné et injecte les dépendances automatiquement
 * Supporte : injection via constructeur, setter, et attribut (field)
 */
public class AnnotationApplicationContext implements ApplicationContext {

    private final Map<String, Object> beans = new HashMap<>();

    public AnnotationApplicationContext(String packageToScan) throws Exception {
        scanAndCreateBeans(packageToScan);
        injectDependencies();
    }

    private void scanAndCreateBeans(String packageToScan) throws Exception {
        Reflections reflections = new Reflections(packageToScan);
        Set<Class<?>> components = reflections.getTypesAnnotatedWith(Component.class);

        for (Class<?> clazz : components) {
            // Essayer d'abord l'injection par constructeur annoté
            Object instance = tryConstructorInjection(clazz);
            if (instance == null) {
                instance = clazz.getConstructor().newInstance();
            }

            String beanName = clazz.getAnnotation(Component.class).value();
            if (beanName.isEmpty()) {
                beanName = Character.toLowerCase(clazz.getSimpleName().charAt(0))
                        + clazz.getSimpleName().substring(1);
            }
            beans.put(beanName, instance);
        }
    }

    private Object tryConstructorInjection(Class<?> clazz) throws Exception {
        for (Constructor<?> ctor : clazz.getConstructors()) {
            if (ctor.isAnnotationPresent(Autowired.class)) {
                return null;
            }
        }
        return null;
    }

    private void injectDependencies() throws Exception {
        for (Object bean : beans.values()) {
            injectFields(bean);
            injectSetters(bean);
            injectConstructors(bean);
        }
    }

    private void injectFields(Object bean) throws Exception {
        for (Field field : bean.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Autowired.class)) {
                Object dep = findBeanByType(field.getType());
                if (dep != null) {
                    field.setAccessible(true);
                    field.set(bean, dep);
                }
            }
        }
    }

    private void injectSetters(Object bean) throws Exception {
        for (Method method : bean.getClass().getMethods()) {
            if (method.isAnnotationPresent(Autowired.class) && method.getParameterCount() == 1) {
                Object dep = findBeanByType(method.getParameterTypes()[0]);
                if (dep != null) {
                    method.invoke(bean, dep);
                }
            }
        }
    }

    private void injectConstructors(Object bean) throws Exception {
        for (Constructor<?> ctor : bean.getClass().getConstructors()) {
            if (ctor.isAnnotationPresent(Autowired.class)) {
                Object[] args = new Object[ctor.getParameterCount()];
                Class<?>[] types = ctor.getParameterTypes();
                for (int i = 0; i < types.length; i++) {
                    args[i] = findBeanByType(types[i]);
                }
                Object newInstance = ctor.newInstance(args);
                String beanName = bean.getClass().getAnnotation(Component.class).value();
                if (beanName.isEmpty()) {
                    beanName = Character.toLowerCase(bean.getClass().getSimpleName().charAt(0))
                            + bean.getClass().getSimpleName().substring(1);
                }
                beans.put(beanName, newInstance);
                break;
            }
        }
    }

    private Object findBeanByType(Class<?> type) {
        for (Object bean : beans.values()) {
            if (type.isAssignableFrom(bean.getClass())) {
                return bean;
            }
        }
        return null;
    }

    @Override
    public Object getBean(String beanName) {
        return beans.get(beanName);
    }
}
