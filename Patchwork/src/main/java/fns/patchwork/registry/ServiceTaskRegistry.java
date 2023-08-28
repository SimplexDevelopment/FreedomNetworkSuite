/*
 * This file is part of Freedom-Network-Suite - https://github.com/AtlasMediaGroup/Freedom-Network-Suite
 * Copyright (C) 2023 Total Freedom Server Network and contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package fns.patchwork.registry;

import fns.patchwork.service.Service;
import fns.patchwork.service.ServiceSubscription;
import fns.patchwork.service.SubscriptionProvider;
import fns.patchwork.service.Task;
import fns.patchwork.service.TaskSubscription;
import java.util.ArrayList;
import java.util.List;
import org.jetbrains.annotations.Nullable;

/**
 * A registry for all services and tasks registered with Patchwork.
 * <br>
 * This class is <b>not</b> thread-safe, and should only be accessed from the main server thread.
 * <br>
 * <br>
 * <b>Services</b> are tickable tasks which execute every single game tick. They are registered using
 * {@link #registerService(ServiceSubscription)} and can be started using {@link #startService(Class)}.
 * <br>
 * <br>
 * <b>Tasks</b> are runnable tasks which execute at the provided times in the {@link Task} and
 * {@link TaskSubscription} classes. These define whether the Task is repeating, delayed, or just a one-time task. Tasks
 * are registered using {@link #registerTask(TaskSubscription)} and can be started using {@link #startTask(Class)}.
 * <br>
 * <br>
 * <b>ServiceSubscriptions</b> and <b>TaskSubscriptions</b> can both be easily obtained using the
 * {@link SubscriptionProvider} utility class.
 *
 * @see Service
 * @see Task
 * @see ServiceSubscription
 * @see TaskSubscription
 * @see SubscriptionProvider
 */
public class ServiceTaskRegistry
{
    /**
     * A list of all services registered with the registry.
     */
    private final List<ServiceSubscription<?>> services;
    /**
     * A list of all tasks registered with the registry.
     */
    private final List<TaskSubscription<?>> tasks;

    /**
     * Creates a new instance of the registry and initializes the service and task lists to new empty
     * {@link ArrayList}s.
     */
    public ServiceTaskRegistry()
    {
        this.services = new ArrayList<>();
        this.tasks = new ArrayList<>();
    }

    /**
     * Starts all services registered with the registry.
     * <br>
     * This method should be <i>avoided</i>, due to the fact that <b><i>modules may have registered their services after
     * this method has already been called.</i></b> In this case, it is preferred to start each service using
     * {@link #startService(Class)}.
     * <br>
     * However, <i><b>Patchwork calls this method when the server is starting up</b></i>, as Patchwork is the central
     * resource manager for registered tasks and services. Patchwork will call this method on the first server tick, so
     * unless you are registering services <b>AND</b> starting them <b>POST WORLD</b>, you do not need to worry about
     * starting your services.
     */
    public void startAllServices()
    {
        for (final ServiceSubscription<?> service : this.services)
        {
            service.start();
        }
    }

    /**
     * Starts all tasks registered with the registry.
     * <br>
     * This method should be <i>avoided</i>, due to the fact that <b><i>modules may have registered their tasks after
     * this method has already been called.</i></b> In this case, it is preferred to start each task using
     * {@link #startTask(Class)}.
     * <br>
     * However, <i><b>Patchwork calls this method when the server is starting up</b></i>, as Patchwork is the central
     * resource manager for registered tasks and services. Patchwork will call this method on the first server tick, so
     * unless you are registering tasks <b>AND</b> starting them <b>POST WORLD</b>, you do not need to worry about
     * starting your tasks.
     */
    public void startAllTasks()
    {
        for (final TaskSubscription<?> task : this.tasks)
        {
            task.start();
        }
    }

    /**
     * Stops all services registered with the registry.
     * <br>
     * This method should be <i>avoided</i>, due to the fact that <b><i>modules should be handling their own
     * registrations</i></b>. It is preferred to use {@link #stopService(Class)} for each service you would like to
     * stop.
     * <br>
     * However, <b><i>Patchwork calls this method when the server is shutting down</i></b>, or when Patchwork is being
     * disabled, as Patchwork is the central resource manager for registered tasks and services. Unless you are
     * <b>modifying service states while the server is running</b>, you do not need to worry about disabling or
     * unregistering your services.
     */
    public void stopAllServices()
    {
        for (final ServiceSubscription<?> service : this.services)
        {
            service.stop();
        }
    }

    /**
     * Stops all tasks registered with the registry.
     * <br>
     * This method should be <i>avoided</i>, due to the fact that <b><i>modules should be handling their own
     * registrations</i></b>. It is preferred to use {@link #stopTask(Class)} for each task you would like to stop.
     * <br>
     * However, <b><i>Patchwork calls this method when the server is shutting down</i></b>, or when Patchwork is being
     * disabled, as Patchwork is the central resource manager for registered tasks and services. Unless you are
     * <b>modifying task states while the server is running</b>, you do not need to worry about disabling or
     * unregistering your tasks.
     */
    public void stopAllTasks()
    {
        for (final TaskSubscription<?> task : this.tasks)
        {
            task.stop();
        }
    }

    /**
     * Registers a service with the registry.
     * <br>
     * <i>Services must be registered using <b>ServiceSubscriptions</b></i>, which can be easily obtained through the
     * {@link SubscriptionProvider} utility class.
     *
     * @param service The service you are trying to register.
     * @param <T>     A generic type for type inference of the service being registered.
     */
    public <T extends Service> void registerService(final ServiceSubscription<T> service)
    {
        this.services.add(service);
    }

    /**
     * Registers a task with the registry.
     * <br>
     * <i>Tasks must be registered using <b>TaskSubscriptions</b></i>, which can be easily obtained through the
     * {@link SubscriptionProvider} utility class.
     *
     * @param task The task you are trying to register.
     * @param <T>  A generic type for type inference of the task being registered.
     */
    public <T extends Task> void registerTask(final TaskSubscription<T> task)
    {
        this.tasks.add(task);
    }

    /**
     * Starts a service using the specified {@link Service} class.
     * <br>
     * <i>The service should already be registered with the registry as a <b>ServiceSubscription</b></i>.
     *
     * @param clazz The class of the service you are trying to start.
     * @see ServiceSubscription
     * @see #registerService(ServiceSubscription)
     */
    public void startService(final Class<? extends Service> clazz)
    {
        this.getService(clazz)
            .start();
    }

    /**
     * Gets a {@link ServiceSubscription} from the registry using the specified class.
     * <br>
     * <b>The class should be the <u>service class you are trying to locate</u>, not the class for the subscription
     * itself</b>.
     * <br>
     * <i>The service should have been registered previously as a <b>ServiceSubscription</b></i>.
     *
     * @param clazz The class of the service you are trying to locate.
     * @param <T>   A generic type for type inference of the service requested.
     * @return The {@link ServiceSubscription} for the specified class, or null if it could not be found.
     * @see #registerService(ServiceSubscription)
     * @see ServiceSubscription
     */
    @Nullable
    public <T extends Service> ServiceSubscription<T> getService(final Class<T> clazz)
    {
        for (final ServiceSubscription<?> service : this.services)
        {
            if (service.getService()
                       .getClass()
                       .equals(clazz))
            {
                return (ServiceSubscription<T>) service;
            }
        }
        return null;
    }

    /**
     * Stops a service using the specified {@link Service} class.
     * <br>
     * <i>The service should already be registered with the registry as a <b>ServiceSubscription</b></i>.
     *
     * @param clazz The class of the service you are trying to stop.
     * @see #registerService(ServiceSubscription)
     * @see ServiceSubscription
     */
    public void stopService(final Class<? extends Service> clazz)
    {
        this.getService(clazz)
            .stop();
    }

    /**
     * Starts a task using the specified {@link Task} class.
     * <br>
     * <i>The task should already be registered with the registry as a <b>TaskSubscription</b></i>.
     *
     * @param clazz The class of the task you are trying to start.
     * @see #registerTask(TaskSubscription)
     * @see TaskSubscription
     */
    public void startTask(final Class<? extends Task> clazz)
    {
        this.getTask(clazz)
            .start();
    }

    /**
     * Gets a {@link TaskSubscription} from the registry using the specified class.
     * <br>
     * <b>The class should be the <u>task class you are trying to locate</u>, not the class for the subscription
     * itself</b>.
     * <br>
     * <i>The task should have been registered previously as a <b>TaskSubscription</b></i>.
     *
     * @param clazz The class of the task you are trying to locate.
     * @param <T>   A generic type for type inference of the task requested.
     * @return The {@link TaskSubscription} for the specified class, or null if it could not be found.
     * @see #registerTask(TaskSubscription)
     * @see TaskSubscription
     */
    public <T extends Task> TaskSubscription<T> getTask(final Class<T> clazz)
    {
        for (final TaskSubscription<?> task : this.tasks)
        {
            if (task.getTask()
                    .getClass()
                    .equals(clazz))
            {
                return (TaskSubscription<T>) task;
            }
        }
        return null;
    }

    /**
     * Stops a task using the specified {@link Task} class.
     * <br>
     * <i>The task should already be registered with the registry as a <b>TaskSubscription</b></i>.
     *
     * @param clazz The class of the task you are trying to stop.
     * @see #registerTask(TaskSubscription)
     * @see TaskSubscription
     */
    public void stopTask(final Class<? extends Task> clazz)
    {
        this.getTask(clazz)
            .stop();
    }

    /**
     * Unregisters a service from the registry.
     * <br>
     * <i>The service should have been registered previously as a <b>ServiceSubscription</b></i>.
     *
     * @param clazz The service you are trying to unregister.
     * @see #registerService(ServiceSubscription)
     * @see ServiceSubscription
     */
    public void unregisterService(final Class<? extends Service> clazz)
    {
        this.services.remove(getService(clazz));
    }

    /**
     * Unregisters a task from the registry.
     * <br>
     * <i>The task should have been registered previously as a <b>TaskSubscription</b></i>.
     *
     * @param clazz The task you are trying to unregister.
     * @see #registerTask(TaskSubscription)
     * @see TaskSubscription
     */
    public void unregisterTask(final Class<? extends Task> clazz)
    {
        this.tasks.remove(getTask(clazz));
    }
}
