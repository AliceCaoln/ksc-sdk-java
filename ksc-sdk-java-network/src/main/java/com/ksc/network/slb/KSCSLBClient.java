package com.ksc.network.slb;

import com.ksc.*;
import com.ksc.auth.AWSCredentials;
import com.ksc.auth.AWSCredentialsProvider;
import com.ksc.auth.DefaultAWSCredentialsProviderChain;
import com.ksc.http.DefaultErrorResponseHandler;
import com.ksc.http.ExecutionContext;
import com.ksc.http.HttpResponseHandler;
import com.ksc.http.StaxResponseHandler;
import com.ksc.internal.StaticCredentialsProvider;
import com.ksc.metrics.RequestMetricCollector;
import com.ksc.network.slb.model.*;
import com.ksc.network.slb.model.transform.*;
import com.ksc.transform.LegacyErrorUnmarshaller;
import com.ksc.transform.StandardErrorUnmarshaller;
import com.ksc.transform.Unmarshaller;
import com.ksc.util.CredentialUtils;
import com.ksc.util.KscRequestMetrics;
import com.ksc.util.KscRequestMetrics.Field;
import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;

public class KSCSLBClient extends KscWebServiceClient implements KSCSLB{
	/** Provider for AWS credentials. */
	private AWSCredentialsProvider kscCredentialsProvider;

	/** Default signing name for the service. */
	private static final String DEFAULT_SIGNING_NAME = "slb";

	/** The region metadata service name for computing region endpoints. */
	private static final String DEFAULT_ENDPOINT_PREFIX = "slb";

	/**
	 * Client configuration factory providing ClientConfigurations tailored to
	 * this client
	 */
	protected static final ClientConfigurationFactory configFactory = new ClientConfigurationFactory();

	/**
	 * List of exception unmarshallers for all modeled exceptions
	 */
	protected final List<Unmarshaller<KscServiceException, Node>> exceptionUnmarshallers = new ArrayList<Unmarshaller<KscServiceException, Node>>();

	/**
	 * Constructs a new client to invoke service methods on SLB. A credentials
	 * provider chain will be used that searches for credentials in this order:
	 * <ul>
	 * <li>Environment Variables - AWS_ACCESS_KEY_ID and AWS_SECRET_KEY</li>
	 * <li>Java System Properties - aws.accessKeyId and aws.secretKey</li>
	 * <li>Instance profile credentials delivered through the SLB metadata
	 * service</li>
	 * </ul>
	 *
	 * <p>
	 * All service calls made using this new client object are blocking, and
	 * will not return until the service call completes.
	 *
	 * @see DefaultAWSCredentialsProviderChain
	 */
	public KSCSLBClient() {
		this(new DefaultAWSCredentialsProviderChain(), configFactory.getConfig());
	}

	/**
	 * Constructs a new client to invoke service methods on SLB. A credentials
	 * provider chain will be used that searches for credentials in this order:
	 * <ul>
	 * <li>Environment Variables - AWS_ACCESS_KEY_ID and AWS_SECRET_KEY</li>
	 * <li>Java System Properties - aws.accessKeyId and aws.secretKey</li>
	 * <li>Instance profile credentials delivered through the SLB metadata
	 * service</li>
	 * </ul>
	 *
	 * <p>
	 * All service calls made using this new client object are blocking, and
	 * will not return until the service call completes.
	 *
	 * @param clientConfiguration
	 *            The client configuration options controlling how this client
	 *            connects to KSC (ex: proxy settings, retry counts, etc.).
	 *
	 * @see DefaultAWSCredentialsProviderChain
	 */
	public KSCSLBClient(ClientConfiguration clientConfiguration) {
		this(new DefaultAWSCredentialsProviderChain(), clientConfiguration);
	}

	/**
	 * Constructs a new client to invoke service methods on SLB using the
	 * specified AWS account credentials.
	 *
	 * <p>
	 * All service calls made using this new client object are blocking, and
	 * will not return until the service call completes.
	 *
	 * @param awsCredentials
	 *            The AWS credentials (access key ID and secret key) to use when
	 *            authenticating with AWS services.
	 */
	public KSCSLBClient(AWSCredentials awsCredentials) {
		this(awsCredentials, configFactory.getConfig());
	}

	/**
	 * Constructs a new client to invoke service methods on SLB using the
	 * specified AWS account credentials and client configuration options.
	 *
	 * <p>
	 * All service calls made using this new client object are blocking, and
	 * will not return until the service call completes.
	 *
	 * @param awsCredentials
	 *            The AWS credentials (access key ID and secret key) to use when
	 *            authenticating with AWS services.
	 * @param clientConfiguration
	 *            The client configuration options controlling how this client
	 *            connects to SLB (ex: proxy settings, retry counts, etc.).
	 */
	public KSCSLBClient(AWSCredentials awsCredentials, ClientConfiguration clientConfiguration) {
		super(clientConfiguration);
		this.kscCredentialsProvider = new StaticCredentialsProvider(awsCredentials);
		init();
	}

	/**
	 * Constructs a new client to invoke service methods on SLB using the
	 * specified AWS account credentials provider.
	 *
	 * <p>
	 * All service calls made using this new client object are blocking, and
	 * will not return until the service call completes.
	 *
	 * @param awsCredentialsProvider
	 *            The AWS credentials provider which will provide credentials to
	 *            authenticate requests with KSC services.
	 */
	public KSCSLBClient(AWSCredentialsProvider awsCredentialsProvider) {
		this(awsCredentialsProvider, configFactory.getConfig());
	}

	/**
	 * Constructs a new client to invoke service methods on SLB using the
	 * specified AWS account credentials provider and client configuration
	 * options.
	 *
	 * <p>
	 * All service calls made using this new client object are blocking, and
	 * will not return until the service call completes.
	 *
	 * @param awsCredentialsProvider
	 *            The AWS credentials provider which will provide credentials to
	 *            authenticate requests with KSC services.
	 * @param clientConfiguration
	 *            The client configuration options controlling how this client
	 *            connects to SLB (ex: proxy settings, retry counts, etc.).
	 */
	public KSCSLBClient(AWSCredentialsProvider awsCredentialsProvider, ClientConfiguration clientConfiguration) {
		this(awsCredentialsProvider, clientConfiguration, null);
	}

	/**
	 * Constructs a new client to invoke service methods on SLB using the
	 * specified KSC account credentials provider, client configuration options,
	 * and request metric collector.
	 *
	 * <p>
	 * All service calls made using this new client object are blocking, and
	 * will not return until the service call completes.
	 *
	 * @param awsCredentialsProvider
	 *            The AWS credentials provider which will provide credentials to
	 *            authenticate requests with KSC services.
	 * @param clientConfiguration
	 *            The client configuration options controlling how this client
	 *            connects to SLB (ex: proxy settings, retry counts, etc.).
	 * @param requestMetricCollector
	 *            optional request metric collector
	 */
	public KSCSLBClient(AWSCredentialsProvider awsCredentialsProvider, ClientConfiguration clientConfiguration,
			RequestMetricCollector requestMetricCollector) {
		super(clientConfiguration, requestMetricCollector);
		this.kscCredentialsProvider = awsCredentialsProvider;
		init();
	}

	@Override
	public DescribeLoadBalancersResult describeLoadBalancers(DescribeLoadBalancersRequest describeLoadBalancersRequest) {
		ExecutionContext executionContext = createExecutionContext(describeLoadBalancersRequest);
		KscRequestMetrics kscRequestMetrics = executionContext.getKscRequestMetrics();
		kscRequestMetrics.startEvent(Field.ClientExecuteTime);
		Request<DescribeLoadBalancersRequest> request = null;
		Response<DescribeLoadBalancersResult> response = null;
		try {
			kscRequestMetrics.startEvent(Field.RequestMarshallTime);
			try {
				request = new DescribeLoadBalancersRequestMarshaller()
						.marshall(describeLoadBalancersRequest);
				// Binds the request metrics to the current request.
				request.setKscRequestMetrics(kscRequestMetrics);
			} finally {
				kscRequestMetrics.endEvent(Field.RequestMarshallTime);
			}
			StaxResponseHandler<DescribeLoadBalancersResult> responseHandler = new StaxResponseHandler<DescribeLoadBalancersResult>(
					new DescribeLoadBalancersResultStaxUnmarshaller());
			response = invoke(request, responseHandler, executionContext);

			return response.getKscResponse();
		} finally {
			endClientExecution(kscRequestMetrics, request, response);
		}
	}

	@Override
	public CreateLoadBalancerResult createLoadBalancer(CreateLoadBalancerRequest createLoadBalancerRequest) {
		ExecutionContext executionContext = createExecutionContext(createLoadBalancerRequest);
		KscRequestMetrics kscRequestMetrics = executionContext.getKscRequestMetrics();
		kscRequestMetrics.startEvent(Field.ClientExecuteTime);
		Request<CreateLoadBalancerRequest> request = null;
		Response<CreateLoadBalancerResult> response = null;
		try {
			kscRequestMetrics.startEvent(Field.RequestMarshallTime);
			try {
				request = new CreateLoadBalancerRequestMarshaller()
						.marshall(createLoadBalancerRequest);
				// Binds the request metrics to the current request.
				request.setKscRequestMetrics(kscRequestMetrics);
			} finally {
				kscRequestMetrics.endEvent(Field.RequestMarshallTime);
			}
			StaxResponseHandler<CreateLoadBalancerResult> responseHandler = new StaxResponseHandler<CreateLoadBalancerResult>(
					new CreateLoadBalancerResultStaxUnmarshaller());
			response = invoke(request, responseHandler, executionContext);

			return response.getKscResponse();
		} finally {
			endClientExecution(kscRequestMetrics, request, response);
		}
	}

	@Override
    public DeleteLoadBalancerResult deleteLoadBalancer(DeleteLoadBalancerRequest deleteLoadBalancerRequest) {
	    ExecutionContext executionContext = createExecutionContext(deleteLoadBalancerRequest);
	    KscRequestMetrics kscRequestMetrics = executionContext.getKscRequestMetrics();
	    kscRequestMetrics.startEvent(Field.ClientExecuteTime);
	    Request<DeleteLoadBalancerRequest> request = null;
	    Response<DeleteLoadBalancerResult> response = null;
	    try {
	        kscRequestMetrics.startEvent(Field.RequestMarshallTime);
	        try {
	            request = new DeleteLoadBalancerRequestMarshaller()
                        .marshall(deleteLoadBalancerRequest);
	            request.setKscRequestMetrics(kscRequestMetrics);
            }finally {
	            kscRequestMetrics.endEvent(Field.RequestMarshallTime);
            }
            StaxResponseHandler<DeleteLoadBalancerResult> responseHandler = new StaxResponseHandler<DeleteLoadBalancerResult>(
                    new DeleteLoadBalancerResultStaxUnmarshaller());
	        response = invoke(request, responseHandler, executionContext);

	        return response.getKscResponse();
        } finally {
	        endClientExecution(kscRequestMetrics, request, response);
        }
    }

    @Override
    public ModifyLoadBalancerResult modifyLoadBalancer(ModifyLoadBalancerRequest modifyLoadBalancerRequest) {
	    ExecutionContext executionContext = createExecutionContext(modifyLoadBalancerRequest);
	    KscRequestMetrics kscRequestMetrics = executionContext.getKscRequestMetrics();
	    kscRequestMetrics.startEvent(Field.ClientExecuteTime);
	    Request<ModifyLoadBalancerRequest> request = null;
	    Response<ModifyLoadBalancerResult> response = null;
	    try {
	        kscRequestMetrics.startEvent(Field.RequestMarshallTime);
	        try {
	            request = new ModifyLoadBalancerRequestMarshaller().marshall(modifyLoadBalancerRequest);
	            request.setKscRequestMetrics(kscRequestMetrics);
            }finally {
	            kscRequestMetrics.endEvent(Field.RequestMarshallTime);
            }
            StaxResponseHandler<ModifyLoadBalancerResult> responseHandler = new StaxResponseHandler<ModifyLoadBalancerResult>(
                    new ModifyLoadBalancerResultStaxUnmarshaller());
	        response = invoke(request, responseHandler, executionContext);

	        return response.getKscResponse();
        }finally {
	        endClientExecution(kscRequestMetrics,request,response);
        }
    }

	@Override
    public CreateListenersResult createListeners(CreateListenersRequest createListenersRequest) {
		ExecutionContext executionContext = createExecutionContext(createListenersRequest);
		KscRequestMetrics kscRequestMetrics = executionContext.getKscRequestMetrics();
		kscRequestMetrics.startEvent(Field.ClientExecuteTime);
		Request<CreateListenersRequest> request = null;
		Response<CreateListenersResult> response = null;
		try {
			kscRequestMetrics.startEvent(Field.RequestMarshallTime);
			try {
				request = new CreateListenersRequestMarshaller()
						.marshall(createListenersRequest);
				// Binds the request metrics to the current request.
				request.setKscRequestMetrics(kscRequestMetrics);
			} finally {
				kscRequestMetrics.endEvent(Field.RequestMarshallTime);
			}
			StaxResponseHandler<CreateListenersResult> responseHandler = new StaxResponseHandler<CreateListenersResult>(
					new CreateListenersResultStaxUnmarshaller());
			response = invoke(request, responseHandler, executionContext);

			return response.getKscResponse();
		} finally {
			endClientExecution(kscRequestMetrics, request, response);
		}
	}

	@Override
	public ModifyListenersResult modifyListeners(ModifyListenersRequest modifyListenersRequest) {
		ExecutionContext executionContext = createExecutionContext(modifyListenersRequest);
		KscRequestMetrics kscRequestMetrics = executionContext.getKscRequestMetrics();
		kscRequestMetrics.startEvent(Field.ClientExecuteTime);
		Request<ModifyListenersRequest> request = null;
		Response<ModifyListenersResult> response = null;
		try {
			kscRequestMetrics.startEvent(Field.RequestMarshallTime);
			try {
				request = new ModifyListenersRequestMarshaller().marshall(modifyListenersRequest);
				request.setKscRequestMetrics(kscRequestMetrics);
			}finally {
				kscRequestMetrics.endEvent(Field.RequestMarshallTime);
			}
			StaxResponseHandler<ModifyListenersResult> responseHandler = new StaxResponseHandler<ModifyListenersResult>(
					new ModifyListenersResultStaxUnmarshaller());
			response = invoke(request, responseHandler, executionContext);

			return response.getKscResponse();
		}finally {
			endClientExecution(kscRequestMetrics,request,response);
		}
	}

    @Override
    public DeleteListenersResult deleteListeners(DeleteListenersRequest deleteListenersRequest) {
        ExecutionContext executionContext = createExecutionContext(deleteListenersRequest);
        KscRequestMetrics kscRequestMetrics = executionContext.getKscRequestMetrics();
        kscRequestMetrics.startEvent(Field.ClientExecuteTime);
        Request<DeleteListenersRequest> request = null;
        Response<DeleteListenersResult> response = null;
        try {
            kscRequestMetrics.startEvent(Field.RequestMarshallTime);
            try {
                request = new DeleteListenersRequestMarshaller()
                        .marshall(deleteListenersRequest);
                request.setKscRequestMetrics(kscRequestMetrics);
            }finally {
                kscRequestMetrics.endEvent(Field.RequestMarshallTime);
            }
            StaxResponseHandler<DeleteListenersResult> responseHandler = new StaxResponseHandler<DeleteListenersResult>(
                    new DeleteListenersResultStaxUnmarshaller());
            response = invoke(request, responseHandler, executionContext);

            return response.getKscResponse();
        } finally {
            endClientExecution(kscRequestMetrics, request, response);
        }
    }

	@Override
	public DescribeListenersResult describeListeners(DescribeListenersRequest describeListenersRequest) {
		ExecutionContext executionContext = createExecutionContext(describeListenersRequest);
		KscRequestMetrics kscRequestMetrics = executionContext.getKscRequestMetrics();
		kscRequestMetrics.startEvent(Field.ClientExecuteTime);
		Request<DescribeListenersRequest> request = null;
		Response<DescribeListenersResult> response = null;
		try {
			kscRequestMetrics.startEvent(Field.RequestMarshallTime);
			try {
				request = new DescribeListenersRequestMarshaller()
						.marshall(describeListenersRequest);
				// Binds the request metrics to the current request.
				request.setKscRequestMetrics(kscRequestMetrics);
			} finally {
				kscRequestMetrics.endEvent(Field.RequestMarshallTime);
			}
			StaxResponseHandler<DescribeListenersResult> responseHandler = new StaxResponseHandler<DescribeListenersResult>(
					new DescribeListenersResultStaxUnmarshaller());
			response = invoke(request, responseHandler, executionContext);

			return response.getKscResponse();
		} finally {
			endClientExecution(kscRequestMetrics, request, response);
		}
	}

    @Override
    public ConfigureHealthCheckResult configureHealthCheck(ConfigureHealthCheckRequest configureHealthCheckRequest) {
        ExecutionContext executionContext = createExecutionContext(configureHealthCheckRequest);
        KscRequestMetrics kscRequestMetrics = executionContext.getKscRequestMetrics();
        kscRequestMetrics.startEvent(Field.ClientExecuteTime);
        Request<ConfigureHealthCheckRequest> request = null;
        Response<ConfigureHealthCheckResult> response = null;
        try {
            kscRequestMetrics.startEvent(Field.RequestMarshallTime);
            try {
                request = new ConfigureHealthCheckRequestMarshaller()
                        .marshall(configureHealthCheckRequest);
                request.setKscRequestMetrics(kscRequestMetrics);
            }finally {
                kscRequestMetrics.endEvent(Field.RequestMarshallTime);
            }
            StaxResponseHandler<ConfigureHealthCheckResult> responseHandler = new StaxResponseHandler<ConfigureHealthCheckResult>(
                    new ConfigureHealthCheckResultStaxUnmarshaller());
            response = invoke(request, responseHandler, executionContext);

            return response.getKscResponse();
        } finally {
            endClientExecution(kscRequestMetrics, request, response);
        }
    }

	@Override
	public ModifyHealthCheckResult modifyHealthCheck(ModifyHealthCheckRequest modifyHealthCheckRequest) {
		ExecutionContext executionContext = createExecutionContext(modifyHealthCheckRequest);
		KscRequestMetrics kscRequestMetrics = executionContext.getKscRequestMetrics();
		kscRequestMetrics.startEvent(Field.ClientExecuteTime);
		Request<ModifyHealthCheckRequest> request = null;
		Response<ModifyHealthCheckResult> response = null;
		try {
			kscRequestMetrics.startEvent(Field.RequestMarshallTime);
			try {
				request = new ModifyHealthCheckRequestMarshaller().marshall(modifyHealthCheckRequest);
				request.setKscRequestMetrics(kscRequestMetrics);
			}finally {
				kscRequestMetrics.endEvent(Field.RequestMarshallTime);
			}
			StaxResponseHandler<ModifyHealthCheckResult> responseHandler = new StaxResponseHandler<ModifyHealthCheckResult>(
					new ModifyHealthCheckResultStaxUnmarshaller());
			response = invoke(request, responseHandler, executionContext);

			return response.getKscResponse();
		}finally {
			endClientExecution(kscRequestMetrics,request,response);
		}
	}

    @Override
    public DeleteHealthCheckResult deleteHealthCheck(DeleteHealthCheckRequest deleteHealthCheckRequest) {
        ExecutionContext executionContext = createExecutionContext(deleteHealthCheckRequest);
        KscRequestMetrics kscRequestMetrics = executionContext.getKscRequestMetrics();
        kscRequestMetrics.startEvent(Field.ClientExecuteTime);
        Request<DeleteHealthCheckRequest> request = null;
        Response<DeleteHealthCheckResult> response = null;
        try {
            kscRequestMetrics.startEvent(Field.RequestMarshallTime);
            try {
                request = new DeleteHealthCheckRequestMarshaller()
                        .marshall(deleteHealthCheckRequest);
                request.setKscRequestMetrics(kscRequestMetrics);
            }finally {
                kscRequestMetrics.endEvent(Field.RequestMarshallTime);
            }
            StaxResponseHandler<DeleteHealthCheckResult> responseHandler = new StaxResponseHandler<DeleteHealthCheckResult>(
                    new DeleteHealthCheckResultStaxUnmarshaller());
            response = invoke(request, responseHandler, executionContext);

            return response.getKscResponse();
        } finally {
            endClientExecution(kscRequestMetrics, request, response);
        }
    }

    @Override
    public DescribeHealthChecksResult describeHealthChecks(DescribeHealthChecksRequest describeHealthChecksRequest) {
        ExecutionContext executionContext = createExecutionContext(describeHealthChecksRequest);
        KscRequestMetrics kscRequestMetrics = executionContext.getKscRequestMetrics();
        kscRequestMetrics.startEvent(Field.ClientExecuteTime);
        Request<DescribeHealthChecksRequest> request = null;
        Response<DescribeHealthChecksResult> response = null;
        try {
            kscRequestMetrics.startEvent(Field.RequestMarshallTime);
            try {
                request = new DescribeHealthChecksRequestMarshaller()
                        .marshall(describeHealthChecksRequest);
                // Binds the request metrics to the current request.
                request.setKscRequestMetrics(kscRequestMetrics);
            } finally {
                kscRequestMetrics.endEvent(Field.RequestMarshallTime);
            }
            StaxResponseHandler<DescribeHealthChecksResult> responseHandler = new StaxResponseHandler<DescribeHealthChecksResult>(
                    new DescribeHealthChecksResultStaxUnmarshaller());
            response = invoke(request, responseHandler, executionContext);

            return response.getKscResponse();
        } finally {
            endClientExecution(kscRequestMetrics, request, response);
        }
    }

	@Override
	public RegisterInstancesWithListenerResult registerInstancesWithListener(RegisterInstancesWithListenerRequest registerInstancesWithListenerRequest) {
		ExecutionContext executionContext = createExecutionContext(registerInstancesWithListenerRequest);
		KscRequestMetrics kscRequestMetrics = executionContext.getKscRequestMetrics();
		kscRequestMetrics.startEvent(Field.ClientExecuteTime);
		Request<RegisterInstancesWithListenerRequest> request = null;
		Response<RegisterInstancesWithListenerResult> response = null;
		try {
			kscRequestMetrics.startEvent(Field.RequestMarshallTime);
			try {
				request = new RegisterInstancesWithListenerRequestMarshaller()
						.marshall(registerInstancesWithListenerRequest);
				request.setKscRequestMetrics(kscRequestMetrics);
			}finally {
				kscRequestMetrics.endEvent(Field.RequestMarshallTime);
			}
			StaxResponseHandler<RegisterInstancesWithListenerResult> responseHandler = new StaxResponseHandler<RegisterInstancesWithListenerResult>(
					new RegisterInstancesWithListenerResultStaxUnmarshaller());
			response = invoke(request, responseHandler, executionContext);

			return response.getKscResponse();
		} finally {
			endClientExecution(kscRequestMetrics, request, response);
		}
	}

    @Override
    public ModifyInstancesWithListenerResult modifyInstancesWithListener(ModifyInstancesWithListenerRequest modifyInstancesWithListenerRequest) {
        ExecutionContext executionContext = createExecutionContext(modifyInstancesWithListenerRequest);
        KscRequestMetrics kscRequestMetrics = executionContext.getKscRequestMetrics();
        kscRequestMetrics.startEvent(Field.ClientExecuteTime);
        Request<ModifyInstancesWithListenerRequest> request = null;
        Response<ModifyInstancesWithListenerResult> response = null;
        try {
            kscRequestMetrics.startEvent(Field.RequestMarshallTime);
            try {
                request = new ModifyInstancesWithListenerRequestMarshaller().marshall(modifyInstancesWithListenerRequest);
                request.setKscRequestMetrics(kscRequestMetrics);
            }finally {
                kscRequestMetrics.endEvent(Field.RequestMarshallTime);
            }
            StaxResponseHandler<ModifyInstancesWithListenerResult> responseHandler = new StaxResponseHandler<ModifyInstancesWithListenerResult>(
                    new ModifyInstancesWithListenerResultStaxUnmarshaller());
            response = invoke(request, responseHandler, executionContext);

            return response.getKscResponse();
        }finally {
            endClientExecution(kscRequestMetrics,request,response);
        }
    }

    @Override
    public DeregisterInstancesFromListenerResult deregisterInstancesFromListener(DeregisterInstancesFromListenerRequest deregisterInstancesFromListenerRequest) {
        ExecutionContext executionContext = createExecutionContext(deregisterInstancesFromListenerRequest);
        KscRequestMetrics kscRequestMetrics = executionContext.getKscRequestMetrics();
        kscRequestMetrics.startEvent(Field.ClientExecuteTime);
        Request<DeregisterInstancesFromListenerRequest> request = null;
        Response<DeregisterInstancesFromListenerResult> response = null;
        try {
            kscRequestMetrics.startEvent(Field.RequestMarshallTime);
            try {
                request = new DeregisterInstancesFromListenerRequestMarshaller().marshall(deregisterInstancesFromListenerRequest);
                request.setKscRequestMetrics(kscRequestMetrics);
            }finally {
                kscRequestMetrics.endEvent(Field.RequestMarshallTime);
            }
            StaxResponseHandler<DeregisterInstancesFromListenerResult> responseHandler = new StaxResponseHandler<DeregisterInstancesFromListenerResult>(
                    new DeregisterInstancesFromListenerResultStaxUnmarshaller());
            response = invoke(request, responseHandler, executionContext);

            return response.getKscResponse();
        }finally {
            endClientExecution(kscRequestMetrics,request,response);
        }
    }

    @Override
    public DescribeInstancesWithListenerResult describeInstancesWithListener(DescribeInstancesWithListenerRequest describeInstancesWithListenerRequest) {
        ExecutionContext executionContext = createExecutionContext(describeInstancesWithListenerRequest);
        KscRequestMetrics kscRequestMetrics = executionContext.getKscRequestMetrics();
        kscRequestMetrics.startEvent(Field.ClientExecuteTime);
        Request<DescribeInstancesWithListenerRequest> request = null;
        Response<DescribeInstancesWithListenerResult> response = null;
        try {
            kscRequestMetrics.startEvent(Field.RequestMarshallTime);
            try {
                request = new DescribeInstancesWithListenerRequestMarshaller()
                        .marshall(describeInstancesWithListenerRequest);
                // Binds the request metrics to the current request.
                request.setKscRequestMetrics(kscRequestMetrics);
            } finally {
                kscRequestMetrics.endEvent(Field.RequestMarshallTime);
            }
            StaxResponseHandler<DescribeInstancesWithListenerResult> responseHandler = new StaxResponseHandler<DescribeInstancesWithListenerResult>(
                    new DescribeInstancesWithListenerResultStaxUnmarshaller());
            response = invoke(request, responseHandler, executionContext);

            return response.getKscResponse();
        } finally {
            endClientExecution(kscRequestMetrics, request, response);
        }
    }

	@Override
	public CreateHostHeaderResult createHostHeader(CreateHostHeaderRequest createHostHeaderRequest) {
		ExecutionContext executionContext = createExecutionContext(createHostHeaderRequest);
		KscRequestMetrics kscRequestMetrics = executionContext.getKscRequestMetrics();
		kscRequestMetrics.startEvent(Field.ClientExecuteTime);
		Request<CreateHostHeaderRequest> request = null;
		Response<CreateHostHeaderResult> response = null;
		try {
			kscRequestMetrics.startEvent(Field.RequestMarshallTime);
			try {
				request = new CreateHostHeaderRequestMarshaller()
						.marshall(createHostHeaderRequest);
				request.setKscRequestMetrics(kscRequestMetrics);
			}finally {
				kscRequestMetrics.endEvent(Field.RequestMarshallTime);
			}
			StaxResponseHandler<CreateHostHeaderResult> responseHandler = new StaxResponseHandler<CreateHostHeaderResult>(
					new CreateHostHeaderResultStaxUnMarshaller());
			response = invoke(request, responseHandler, executionContext);

			return response.getKscResponse();
		} finally {
			endClientExecution(kscRequestMetrics, request, response);
		}
	}

	@Override
	public ModifyHostHeaderResult modifyHostHeader(ModifyHostHeaderRequest modifyHostHeaderRequest) {
		ExecutionContext executionContext = createExecutionContext(modifyHostHeaderRequest);
		KscRequestMetrics kscRequestMetrics = executionContext.getKscRequestMetrics();
		kscRequestMetrics.startEvent(Field.ClientExecuteTime);
		Request<ModifyHostHeaderRequest> request = null;
		Response<ModifyHostHeaderResult> response = null;
		try {
			kscRequestMetrics.startEvent(Field.RequestMarshallTime);
			try {
				request = new ModifyHostHeaderRequestMarshaller()
						.marshall(modifyHostHeaderRequest);
				request.setKscRequestMetrics(kscRequestMetrics);
			}finally {
				kscRequestMetrics.endEvent(Field.RequestMarshallTime);
			}
			StaxResponseHandler<ModifyHostHeaderResult> responseHandler = new StaxResponseHandler<ModifyHostHeaderResult>(
					new ModifyHostHeaderResultStaxUnmarshaller());
			response = invoke(request, responseHandler, executionContext);

			return response.getKscResponse();
		} finally {
			endClientExecution(kscRequestMetrics, request, response);
		}
	}

	@Override
	public DeleteHostHeaderResult deleteHostHeader(DeleteHostHeaderRequest deleteHostHeaderRequest) {
		ExecutionContext executionContext = createExecutionContext(deleteHostHeaderRequest);
		KscRequestMetrics kscRequestMetrics = executionContext.getKscRequestMetrics();
		kscRequestMetrics.startEvent(Field.ClientExecuteTime);
		Request<DeleteHostHeaderRequest> request = null;
		Response<DeleteHostHeaderResult> response = null;
		try {
			kscRequestMetrics.startEvent(Field.RequestMarshallTime);
			try {
				request = new DeleteHostHeaderRequestMarshaller()
						.marshall(deleteHostHeaderRequest);
				request.setKscRequestMetrics(kscRequestMetrics);
			}finally {
				kscRequestMetrics.endEvent(Field.RequestMarshallTime);
			}
			StaxResponseHandler<DeleteHostHeaderResult> responseHandler = new StaxResponseHandler<DeleteHostHeaderResult>(
					new DeleteHostHeaderResultStaxUnmarshaller());
			response = invoke(request, responseHandler, executionContext);

			return response.getKscResponse();
		} finally {
			endClientExecution(kscRequestMetrics, request, response);
		}
	}

	@Override
	public DescribeHostHeadersResult describeHostHeader(DescribeHostHeadersRequest describeHostHeadersRequest) {
		ExecutionContext executionContext = createExecutionContext(describeHostHeadersRequest);
		KscRequestMetrics kscRequestMetrics = executionContext.getKscRequestMetrics();
		kscRequestMetrics.startEvent(Field.ClientExecuteTime);
		Request<DescribeHostHeadersRequest> request = null;
		Response<DescribeHostHeadersResult> response = null;
		try {
			kscRequestMetrics.startEvent(Field.RequestMarshallTime);
			try {
				request = new DescribeHostHeadersRequestMarshaller()
						.marshall(describeHostHeadersRequest);
				request.setKscRequestMetrics(kscRequestMetrics);
			}finally {
				kscRequestMetrics.endEvent(Field.RequestMarshallTime);
			}
			StaxResponseHandler<DescribeHostHeadersResult> responseHandler = new StaxResponseHandler<DescribeHostHeadersResult>(
					new DescribeHostHeadersResultStaxUnmarshaller());
			response = invoke(request, responseHandler, executionContext);

			return response.getKscResponse();
		} finally {
			endClientExecution(kscRequestMetrics, request, response);
		}
	}

	@Override
	public CreateLoadBalancerAclResult createLoadBalancerAcl(CreateLoadBalancerAclRequest createLoadBalancerAclRequest) {
		ExecutionContext executionContext = createExecutionContext(createLoadBalancerAclRequest);
		KscRequestMetrics kscRequestMetrics = executionContext.getKscRequestMetrics();
		kscRequestMetrics.startEvent(Field.ClientExecuteTime);
		Request<CreateLoadBalancerAclRequest> request = null;
		Response<CreateLoadBalancerAclResult> response = null;
		try {
			kscRequestMetrics.startEvent(Field.RequestMarshallTime);
			try {
				request = new CreateLoadBalancerAclRequestMarshaller()
						.marshall(createLoadBalancerAclRequest);
				request.setKscRequestMetrics(kscRequestMetrics);
			} finally {
				kscRequestMetrics.endEvent(Field.RequestMarshallTime);
			}
			StaxResponseHandler<CreateLoadBalancerAclResult> responseHandler = new StaxResponseHandler<CreateLoadBalancerAclResult>(
					new CreateLoadBalancerAclResultStaxUnmarshaller());
			response = invoke(request, responseHandler, executionContext);

			return response.getKscResponse();
		} finally {
			endClientExecution(kscRequestMetrics, request, response);
		}
	}

	@Override
	public DeleteLoadBalancerAclResult deleteLoadBalancerAcl(DeleteLoadBalancerAclRequest deleteLoadBalancerAclRequest) {
		ExecutionContext executionContext = createExecutionContext(deleteLoadBalancerAclRequest);
		KscRequestMetrics kscRequestMetrics = executionContext.getKscRequestMetrics();
		kscRequestMetrics.startEvent(Field.ClientExecuteTime);
		Request<DeleteLoadBalancerAclRequest> request = null;
		Response<DeleteLoadBalancerAclResult> response = null;
		try {
			kscRequestMetrics.startEvent(Field.RequestMarshallTime);
			try {
				request = new DeleteLoadBalancerAclRequestMarshaller()
						.marshall(deleteLoadBalancerAclRequest);
				request.setKscRequestMetrics(kscRequestMetrics);
			} finally {
				kscRequestMetrics.endEvent(Field.RequestMarshallTime);
			}
			StaxResponseHandler<DeleteLoadBalancerAclResult> responseHandler = new StaxResponseHandler<DeleteLoadBalancerAclResult>(
					new DeleteLoadBalancerAclResultStaxUnmarshaller());
			response = invoke(request, responseHandler, executionContext);

			return response.getKscResponse();
		} finally {
			endClientExecution(kscRequestMetrics, request, response);
		}
	}

	@Override
	public ModifyLoadBalancerAclResult modifyLoadBalancerAcl(ModifyLoadBalancerAclRequest modifyLoadBalancerAclRequest) {
		ExecutionContext executionContext = createExecutionContext(modifyLoadBalancerAclRequest);
		KscRequestMetrics kscRequestMetrics = executionContext.getKscRequestMetrics();
		kscRequestMetrics.startEvent(Field.ClientExecuteTime);
		Request<ModifyLoadBalancerAclRequest> request = null;
		Response<ModifyLoadBalancerAclResult> response = null;
		try {
			kscRequestMetrics.startEvent(Field.RequestMarshallTime);
			try {
				request = new ModifyLoadBalancerAclRequestMarshaller()
						.marshall(modifyLoadBalancerAclRequest);
				request.setKscRequestMetrics(kscRequestMetrics);
			} finally {
				kscRequestMetrics.endEvent(Field.RequestMarshallTime);
			}
			StaxResponseHandler<ModifyLoadBalancerAclResult> responseHandler = new StaxResponseHandler<ModifyLoadBalancerAclResult>(
					new ModifyLoadBalancerAclResultStaxUnmarshaller());
			response = invoke(request, responseHandler, executionContext);

			return response.getKscResponse();
		} finally {
			endClientExecution(kscRequestMetrics, request, response);
		}
	}

	@Override
	public DescribeLoadBalancerAclsResult describeLoadBalancerAcls(DescribeLoadBalancerAclsRequest describeLoadBalancerAclsRequest) {
		ExecutionContext executionContext = createExecutionContext(describeLoadBalancerAclsRequest);
		KscRequestMetrics kscRequestMetrics = executionContext.getKscRequestMetrics();
		kscRequestMetrics.startEvent(Field.ClientExecuteTime);
		Request<DescribeLoadBalancerAclsRequest> request = null;
		Response<DescribeLoadBalancerAclsResult> response = null;
		try {
			kscRequestMetrics.startEvent(Field.RequestMarshallTime);
			try {
				request = new DescribeLoadBalancerAclsRequestMarshaller()
						.marshall(describeLoadBalancerAclsRequest);
				request.setKscRequestMetrics(kscRequestMetrics);
			} finally {
				kscRequestMetrics.endEvent(Field.RequestMarshallTime);
			}
			StaxResponseHandler<DescribeLoadBalancerAclsResult> responseHandler = new StaxResponseHandler<DescribeLoadBalancerAclsResult>(
					new DescribeLoadBalancerAclsResultStaxUnmarshaller());
			response = invoke(request, responseHandler, executionContext);

			return response.getKscResponse();
		} finally {
			endClientExecution(kscRequestMetrics, request, response);
		}
	}


	@Override
	public DeleteLoadBalancerAclEntryResult deleteLoadBalancerAclEntry(DeleteLoadBalancerAclEntryRequest deleteLoadBalancerAclEntryRequest) {
		ExecutionContext executionContext = createExecutionContext(deleteLoadBalancerAclEntryRequest);
		KscRequestMetrics kscRequestMetrics = executionContext.getKscRequestMetrics();
		kscRequestMetrics.startEvent(Field.ClientExecuteTime);
		Request<DeleteLoadBalancerAclEntryRequest> request = null;
		Response<DeleteLoadBalancerAclEntryResult> response = null;
		try {
			kscRequestMetrics.startEvent(Field.RequestMarshallTime);
			try {
				request = new DeleteLoadBalancerAclEntryRequestMarshaller()
						.marshall(deleteLoadBalancerAclEntryRequest);
				request.setKscRequestMetrics(kscRequestMetrics);
			} finally {
				kscRequestMetrics.endEvent(Field.RequestMarshallTime);
			}
			StaxResponseHandler<DeleteLoadBalancerAclEntryResult> responseHandler = new StaxResponseHandler<DeleteLoadBalancerAclEntryResult>(
					new DeleteLoadBalancerAclEntryResultStaxUnmarshaller());
			response = invoke(request, responseHandler, executionContext);

			return response.getKscResponse();
		} finally {
			endClientExecution(kscRequestMetrics, request, response);
		}
	}

	@Override
	public CreateLoadBalancerAclEntryResult createLoadBalancerAclEntry(CreateLoadBalancerAclEntryRequest createLoadBalancerAclEntryRequest) {
		ExecutionContext executionContext = createExecutionContext(createLoadBalancerAclEntryRequest);
		KscRequestMetrics kscRequestMetrics = executionContext.getKscRequestMetrics();
		kscRequestMetrics.startEvent(Field.ClientExecuteTime);
		Request<CreateLoadBalancerAclEntryRequest> request = null;
		Response<CreateLoadBalancerAclEntryResult> response = null;
		try {
			kscRequestMetrics.startEvent(Field.RequestMarshallTime);
			try {
				request = new CreateLoadBalancerAclEntryRequestMarshaller()
						.marshall(createLoadBalancerAclEntryRequest);
				request.setKscRequestMetrics(kscRequestMetrics);
			} finally {
				kscRequestMetrics.endEvent(Field.RequestMarshallTime);
			}
			StaxResponseHandler<CreateLoadBalancerAclEntryResult> responseHandler = new StaxResponseHandler<CreateLoadBalancerAclEntryResult>(
					new CreateLoadBalancerAclEntryResultStaxUnmarshaller());
			response = invoke(request, responseHandler, executionContext);

			return response.getKscResponse();
		} finally {
			endClientExecution(kscRequestMetrics, request, response);
		}
	}

	@Override
	public AssociateLoadBalancerAclResult associateLoadBalancerAcl(AssociateLoadBalancerAclRequest associateLoadBalancerAclRequest) {
		ExecutionContext executionContext = createExecutionContext(associateLoadBalancerAclRequest);
		KscRequestMetrics kscRequestMetrics = executionContext.getKscRequestMetrics();
		kscRequestMetrics.startEvent(Field.ClientExecuteTime);
		Request<AssociateLoadBalancerAclRequest> request = null;
		Response<AssociateLoadBalancerAclResult> response = null;
		try {
			kscRequestMetrics.startEvent(Field.RequestMarshallTime);
			try {
				request = new AssociateLoadBalancerAclRequestMarshaller()
						.marshall(associateLoadBalancerAclRequest);
				request.setKscRequestMetrics(kscRequestMetrics);
			} finally {
				kscRequestMetrics.endEvent(Field.RequestMarshallTime);
			}
			StaxResponseHandler<AssociateLoadBalancerAclResult> responseHandler = new StaxResponseHandler<AssociateLoadBalancerAclResult>(
					new AssociateLoadBalancerAclResultStaxUnmarshaller());
			response = invoke(request, responseHandler, executionContext);

			return response.getKscResponse();
		} finally {
			endClientExecution(kscRequestMetrics, request, response);
		}
	}

	@Override
	public DisassociateLoadBalancerAclResult disassociateLoadBalancerAcl(DisassociateLoadBalancerAclRequest disassociateLoadBalancerAclRequest) {
		ExecutionContext executionContext = createExecutionContext(disassociateLoadBalancerAclRequest);
		KscRequestMetrics kscRequestMetrics = executionContext.getKscRequestMetrics();
		kscRequestMetrics.startEvent(Field.ClientExecuteTime);
		Request<DisassociateLoadBalancerAclRequest> request = null;
		Response<DisassociateLoadBalancerAclResult> response = null;
		try {
			kscRequestMetrics.startEvent(Field.RequestMarshallTime);
			try {
				request = new DisassociateLoadBalancerAclRequestMarshaller()
						.marshall(disassociateLoadBalancerAclRequest);
				request.setKscRequestMetrics(kscRequestMetrics);
			} finally {
				kscRequestMetrics.endEvent(Field.RequestMarshallTime);
			}
			StaxResponseHandler<DisassociateLoadBalancerAclResult> responseHandler = new StaxResponseHandler<DisassociateLoadBalancerAclResult>(
					new DisassociateLoadBalancerAclResultStaxUnmarshaller());
			response = invoke(request, responseHandler, executionContext);

			return response.getKscResponse();
		} finally {
			endClientExecution(kscRequestMetrics, request, response);
		}
	}
	@Override
	public CreateSlbRuleResult createSlbRule(CreateSlbRuleRequest createSlbRuleRequest) {
		ExecutionContext executionContext = createExecutionContext(createSlbRuleRequest);
		KscRequestMetrics kscRequestMetrics = executionContext.getKscRequestMetrics();
		kscRequestMetrics.startEvent(Field.ClientExecuteTime);
		Request<CreateSlbRuleRequest> request = null;
		Response<CreateSlbRuleResult> response = null;
		try {
			kscRequestMetrics.startEvent(Field.RequestMarshallTime);
			try {
				request = new CreateSlbRuleRequestMarshaller()
						.marshall(createSlbRuleRequest);
				request.setKscRequestMetrics(kscRequestMetrics);
			} finally {
				kscRequestMetrics.endEvent(Field.RequestMarshallTime);
			}
			StaxResponseHandler<CreateSlbRuleResult> responseHandler = new StaxResponseHandler<CreateSlbRuleResult>(
					new CreateSlbRuleResultStaxUnmarshaller());
			response = invoke(request, responseHandler, executionContext);

			return response.getKscResponse();
		} finally {
			endClientExecution(kscRequestMetrics, request, response);
		}
	}

	@Override
	public ModifySlbRuleResult modifySlbRule(ModifySlbRuleRequest modifySlbRuleRequest) {
		ExecutionContext executionContext = createExecutionContext(modifySlbRuleRequest);
		KscRequestMetrics kscRequestMetrics = executionContext.getKscRequestMetrics();
		kscRequestMetrics.startEvent(Field.ClientExecuteTime);
		Request<ModifySlbRuleRequest> request = null;
		Response<ModifySlbRuleResult> response = null;
		try {
			kscRequestMetrics.startEvent(Field.RequestMarshallTime);
			try {
				request = new ModifySlbRuleRequestMarshaller()
						.marshall(modifySlbRuleRequest);
				request.setKscRequestMetrics(kscRequestMetrics);
			} finally {
				kscRequestMetrics.endEvent(Field.RequestMarshallTime);
			}
			StaxResponseHandler<ModifySlbRuleResult> responseHandler = new StaxResponseHandler<ModifySlbRuleResult>(
					new ModifySlbRuleResultStaxUnmarshaller());
			response = invoke(request, responseHandler, executionContext);

			return response.getKscResponse();
		} finally {
			endClientExecution(kscRequestMetrics, request, response);
		}
	}

	@Override
	public DeleteRuleResult deleteRule(DeleteRuleRequest deleteRuleRequest) {
		ExecutionContext executionContext = createExecutionContext(deleteRuleRequest);
		KscRequestMetrics kscRequestMetrics = executionContext.getKscRequestMetrics();
		kscRequestMetrics.startEvent(Field.ClientExecuteTime);
		Request<DeleteRuleRequest> request = null;
		Response<DeleteRuleResult> response = null;
		try {
			kscRequestMetrics.startEvent(Field.RequestMarshallTime);
			try {
				request = new DeleteRuleRequestMarshaller()
						.marshall(deleteRuleRequest);
				request.setKscRequestMetrics(kscRequestMetrics);
			} finally {
				kscRequestMetrics.endEvent(Field.RequestMarshallTime);
			}
			StaxResponseHandler<DeleteRuleResult> responseHandler = new StaxResponseHandler<DeleteRuleResult>(
					new DeleteRuleResultStaxUnmarshaller());
			response = invoke(request, responseHandler, executionContext);

			return response.getKscResponse();
		} finally {
			endClientExecution(kscRequestMetrics, request, response);
		}
	}

	@Override
	public DescribeRulesResult describeRules(DescribeRulesRequest describeRulesRequest) {
		ExecutionContext executionContext = createExecutionContext(describeRulesRequest);
		KscRequestMetrics kscRequestMetrics = executionContext.getKscRequestMetrics();
		kscRequestMetrics.startEvent(Field.ClientExecuteTime);
		Request<DescribeRulesRequest> request = null;
		Response<DescribeRulesResult> response = null;
		try {
			kscRequestMetrics.startEvent(Field.RequestMarshallTime);
			try {
				request = new DescribeRulesRequestMarshaller()
						.marshall(describeRulesRequest);
				request.setKscRequestMetrics(kscRequestMetrics);
			} finally {
				kscRequestMetrics.endEvent(Field.RequestMarshallTime);
			}
			StaxResponseHandler<DescribeRulesResult> responseHandler = new StaxResponseHandler<DescribeRulesResult>(
					new DescribeRulesResultStaxUnmarshaller());
			response = invoke(request, responseHandler, executionContext);

			return response.getKscResponse();
		} finally {
			endClientExecution(kscRequestMetrics, request, response);
		}
	}

	@Override
	public CreateBackendServerGroupResult createBackendServerGroup(CreateBackendServerGroupRequest createBackendServerGroupRequest) {
		ExecutionContext executionContext = createExecutionContext(createBackendServerGroupRequest);
		KscRequestMetrics kscRequestMetrics = executionContext.getKscRequestMetrics();
		kscRequestMetrics.startEvent(Field.ClientExecuteTime);
		Request<CreateBackendServerGroupRequest> request = null;
		Response<CreateBackendServerGroupResult> response = null;
		try {
			kscRequestMetrics.startEvent(Field.RequestMarshallTime);
			try {
				request = new CreateBackendServerGroupRequestMarshaller()
						.marshall(createBackendServerGroupRequest);
				request.setKscRequestMetrics(kscRequestMetrics);
			} finally {
				kscRequestMetrics.endEvent(Field.RequestMarshallTime);
			}
			StaxResponseHandler<CreateBackendServerGroupResult> responseHandler = new StaxResponseHandler<CreateBackendServerGroupResult>(
					new CreateBackendServerGroupResultStaxUnmarshaller());
			response = invoke(request, responseHandler, executionContext);

			return response.getKscResponse();
		} finally {
			endClientExecution(kscRequestMetrics, request, response);
		}
	}

	@Override
	public ModifyBackendServerGroupResult modifyBackendServerGroup(ModifyBackendServerGroupRequest modifyBackendServerGroupRequest) {
		ExecutionContext executionContext = createExecutionContext(modifyBackendServerGroupRequest);
		KscRequestMetrics kscRequestMetrics = executionContext.getKscRequestMetrics();
		kscRequestMetrics.startEvent(Field.ClientExecuteTime);
		Request<ModifyBackendServerGroupRequest> request = null;
		Response<ModifyBackendServerGroupResult> response = null;
		try {
			kscRequestMetrics.startEvent(Field.RequestMarshallTime);
			try {
				request = new ModifyBackendServerGroupRequestMarshaller()
						.marshall(modifyBackendServerGroupRequest);
				request.setKscRequestMetrics(kscRequestMetrics);
			} finally {
				kscRequestMetrics.endEvent(Field.RequestMarshallTime);
			}
			StaxResponseHandler<ModifyBackendServerGroupResult> responseHandler = new StaxResponseHandler<ModifyBackendServerGroupResult>(
					new ModifyBackendServerGroupResultStaxUnmarshaller());
			response = invoke(request, responseHandler, executionContext);

			return response.getKscResponse();
		} finally {
			endClientExecution(kscRequestMetrics, request, response);
		}
	}

	@Override
	public DeleteBackendServerGroupResult deleteBackendServerGroup(DeleteBackendServerGroupRequest deleteBackendServerGroupRequest) {
		ExecutionContext executionContext = createExecutionContext(deleteBackendServerGroupRequest);
		KscRequestMetrics kscRequestMetrics = executionContext.getKscRequestMetrics();
		kscRequestMetrics.startEvent(Field.ClientExecuteTime);
		Request<DeleteBackendServerGroupRequest> request = null;
		Response<DeleteBackendServerGroupResult> response = null;
		try {
			kscRequestMetrics.startEvent(Field.RequestMarshallTime);
			try {
				request = new DeleteBackendServerGroupRequestMarshaller()
						.marshall(deleteBackendServerGroupRequest);
				request.setKscRequestMetrics(kscRequestMetrics);
			} finally {
				kscRequestMetrics.endEvent(Field.RequestMarshallTime);
			}
			StaxResponseHandler<DeleteBackendServerGroupResult> responseHandler = new StaxResponseHandler<DeleteBackendServerGroupResult>(
					new DeleteBackendServerGroupResultStaxUnmarshaller());
			response = invoke(request, responseHandler, executionContext);

			return response.getKscResponse();
		} finally {
			endClientExecution(kscRequestMetrics, request, response);
		}
	}

	@Override
	public DescribeBackendServerGroupsResult describeBackendServerGroups(DescribeBackendServerGroupsRequest describeBackendServerGroupsRequest) {
		ExecutionContext executionContext = createExecutionContext(describeBackendServerGroupsRequest);
		KscRequestMetrics kscRequestMetrics = executionContext.getKscRequestMetrics();
		kscRequestMetrics.startEvent(Field.ClientExecuteTime);
		Request<DescribeBackendServerGroupsRequest> request = null;
		Response<DescribeBackendServerGroupsResult> response = null;
		try {
			kscRequestMetrics.startEvent(Field.RequestMarshallTime);
			try {
				request = new DescribeBackendServerGroupsRequestMarshaller()
						.marshall(describeBackendServerGroupsRequest);
				request.setKscRequestMetrics(kscRequestMetrics);
			} finally {
				kscRequestMetrics.endEvent(Field.RequestMarshallTime);
			}
			StaxResponseHandler<DescribeBackendServerGroupsResult> responseHandler = new StaxResponseHandler<DescribeBackendServerGroupsResult>(
					new DescribeBackendServerGroupsResultStaxUnmarshaller());
			response = invoke(request, responseHandler, executionContext);

			return response.getKscResponse();
		} finally {
			endClientExecution(kscRequestMetrics, request, response);
		}
	}

	@Override
	public RegisterBackendServerResult registerBackendServer(RegisterBackendServerRequest registerBackendServerRequest) {
		ExecutionContext executionContext = createExecutionContext(registerBackendServerRequest);
		KscRequestMetrics kscRequestMetrics = executionContext.getKscRequestMetrics();
		kscRequestMetrics.startEvent(Field.ClientExecuteTime);
		Request<RegisterBackendServerRequest> request = null;
		Response<RegisterBackendServerResult> response = null;
		try {
			kscRequestMetrics.startEvent(Field.RequestMarshallTime);
			try {
				request = new RegisterBackendServerRequestMarshaller()
						.marshall(registerBackendServerRequest);
				request.setKscRequestMetrics(kscRequestMetrics);
			} finally {
				kscRequestMetrics.endEvent(Field.RequestMarshallTime);
			}
			StaxResponseHandler<RegisterBackendServerResult> responseHandler = new StaxResponseHandler<RegisterBackendServerResult>(
					new RegisterBackendServerResultStaxUnmarshaller());
			response = invoke(request, responseHandler, executionContext);

			return response.getKscResponse();
		} finally {
			endClientExecution(kscRequestMetrics, request, response);
		}
	}

	@Override
	public ModifyBackendServerResult modifyBackendServer(ModifyBackendServerRequest modifyBackendServerRequest) {
		ExecutionContext executionContext = createExecutionContext(modifyBackendServerRequest);
		KscRequestMetrics kscRequestMetrics = executionContext.getKscRequestMetrics();
		kscRequestMetrics.startEvent(Field.ClientExecuteTime);
		Request<ModifyBackendServerRequest> request = null;
		Response<ModifyBackendServerResult> response = null;
		try {
			kscRequestMetrics.startEvent(Field.RequestMarshallTime);
			try {
				request = new ModifyBackendServerRequestMarshaller()
						.marshall(modifyBackendServerRequest);
				request.setKscRequestMetrics(kscRequestMetrics);
			} finally {
				kscRequestMetrics.endEvent(Field.RequestMarshallTime);
			}
			StaxResponseHandler<ModifyBackendServerResult> responseHandler = new StaxResponseHandler<ModifyBackendServerResult>(
					new ModifyBackendServerResultStaxUnmarshaller());
			response = invoke(request, responseHandler, executionContext);

			return response.getKscResponse();
		} finally {
			endClientExecution(kscRequestMetrics, request, response);
		}
	}

	@Override
	public DeregisterBackendServerResult deregisterBackendServer(DeregisterBackendServerRequest deregisterBackendServerRequest) {
		ExecutionContext executionContext = createExecutionContext(deregisterBackendServerRequest);
		KscRequestMetrics kscRequestMetrics = executionContext.getKscRequestMetrics();
		kscRequestMetrics.startEvent(Field.ClientExecuteTime);
		Request<DeregisterBackendServerRequest> request = null;
		Response<DeregisterBackendServerResult> response = null;
		try {
			kscRequestMetrics.startEvent(Field.RequestMarshallTime);
			try {
				request = new DeregisterBackendServerRequestMarshaller()
						.marshall(deregisterBackendServerRequest);
				request.setKscRequestMetrics(kscRequestMetrics);
			} finally {
				kscRequestMetrics.endEvent(Field.RequestMarshallTime);
			}
			StaxResponseHandler<DeregisterBackendServerResult> responseHandler = new StaxResponseHandler<DeregisterBackendServerResult>(
					new DeregisterBackendServerResultStaxUnmarshaller());
			response = invoke(request, responseHandler, executionContext);

			return response.getKscResponse();
		} finally {
			endClientExecution(kscRequestMetrics, request, response);
		}
	}

    @Override
    public DescribeBackendServersResult describeBackendServers(DescribeBackendServersRequest describeBackendServersRequest) {
        ExecutionContext executionContext = createExecutionContext(describeBackendServersRequest);
        KscRequestMetrics kscRequestMetrics = executionContext.getKscRequestMetrics();
        kscRequestMetrics.startEvent(Field.ClientExecuteTime);
        Request<DescribeBackendServersRequest> request = null;
        Response<DescribeBackendServersResult> response = null;
        try {
            kscRequestMetrics.startEvent(Field.RequestMarshallTime);
            try {
                request = new DescribeBackendServersRequestMarshaller()
                        .marshall(describeBackendServersRequest);
                request.setKscRequestMetrics(kscRequestMetrics);
            } finally {
                kscRequestMetrics.endEvent(Field.RequestMarshallTime);
            }
            StaxResponseHandler<DescribeBackendServersResult> responseHandler = new StaxResponseHandler<DescribeBackendServersResult>(
                    new DescribeBackendServersResultStaxUnmarshaller());
            response = invoke(request, responseHandler, executionContext);

            return response.getKscResponse();
        } finally {
            endClientExecution(kscRequestMetrics, request, response);
        }
    }


    @Override
    public CreatePrivateLinkServerResult createPrivateLinkServer(CreatePrivateLinkServerRequest createPrivateLinkServerRequest) {
        ExecutionContext executionContext = createExecutionContext(createPrivateLinkServerRequest);
        KscRequestMetrics kscRequestMetrics = executionContext.getKscRequestMetrics();
        kscRequestMetrics.startEvent(Field.ClientExecuteTime);
        Request<CreatePrivateLinkServerRequest> request = null;
        Response<CreatePrivateLinkServerResult> response = null;
        try {
            kscRequestMetrics.startEvent(Field.RequestMarshallTime);
            try {
                request = new CreatePrivateLinkServerRequestMarshaller()
                        .marshall(createPrivateLinkServerRequest);
                request.setKscRequestMetrics(kscRequestMetrics);
            } finally {
                kscRequestMetrics.endEvent(Field.RequestMarshallTime);
            }
            StaxResponseHandler<CreatePrivateLinkServerResult> responseHandler = new StaxResponseHandler<CreatePrivateLinkServerResult>(
                    new CreatePrivateLinkServerResultStaxUnmarshaller());
            response = invoke(request, responseHandler, executionContext);

            return response.getKscResponse();
        } finally {
            endClientExecution(kscRequestMetrics, request, response);
        }
    }

    @Override
    public DeletePrivateLinkServerResult deletePrivateLinkServer(DeletePrivateLinkServerRequest deletePrivateLinkServerRequest) {
        ExecutionContext executionContext = createExecutionContext(deletePrivateLinkServerRequest);
        KscRequestMetrics kscRequestMetrics = executionContext.getKscRequestMetrics();
        kscRequestMetrics.startEvent(Field.ClientExecuteTime);
        Request<DeletePrivateLinkServerRequest> request = null;
        Response<DeletePrivateLinkServerResult> response = null;
        try {
            kscRequestMetrics.startEvent(Field.RequestMarshallTime);
            try {
                request = new DeletePrivateLinkServerRequestMarshaller()
                        .marshall(deletePrivateLinkServerRequest);
                request.setKscRequestMetrics(kscRequestMetrics);
            } finally {
                kscRequestMetrics.endEvent(Field.RequestMarshallTime);
            }
            StaxResponseHandler<DeletePrivateLinkServerResult> responseHandler = new StaxResponseHandler<DeletePrivateLinkServerResult>(
                    new DeletePrivateLinkServerResultStaxUnmarshaller());
            response = invoke(request, responseHandler, executionContext);

            return response.getKscResponse();
        } finally {
            endClientExecution(kscRequestMetrics, request, response);
        }
    }

    @Override
    public DescribePrivateLinkServerResult describePrivateLinkServer(DescribePrivateLinkServerRequest describePrivateLinkServerRequest) {
        ExecutionContext executionContext = createExecutionContext(describePrivateLinkServerRequest);
        KscRequestMetrics kscRequestMetrics = executionContext.getKscRequestMetrics();
        kscRequestMetrics.startEvent(Field.ClientExecuteTime);
        Request<DescribePrivateLinkServerRequest> request = null;
        Response<DescribePrivateLinkServerResult> response = null;
        try {
            kscRequestMetrics.startEvent(Field.RequestMarshallTime);
            try {
                request = new DescribePrivateLinkServerRequestMarshaller()
                        .marshall(describePrivateLinkServerRequest);
                request.setKscRequestMetrics(kscRequestMetrics);
            } finally {
                kscRequestMetrics.endEvent(Field.RequestMarshallTime);
            }
            StaxResponseHandler<DescribePrivateLinkServerResult> responseHandler = new StaxResponseHandler<DescribePrivateLinkServerResult>(
                    new DescribePrivateLinkServerResultStaxUnmarshaller());
            response = invoke(request, responseHandler, executionContext);

            return response.getKscResponse();
        } finally {
            endClientExecution(kscRequestMetrics, request, response);
        }
    }

    @Override
    public ModifyPrivateLinkServerResult modifyPrivateLinkServer(ModifyPrivateLinkServerRequest modifyPrivateLinkServerRequest) {
        ExecutionContext executionContext = createExecutionContext(modifyPrivateLinkServerRequest);
        KscRequestMetrics kscRequestMetrics = executionContext.getKscRequestMetrics();
        kscRequestMetrics.startEvent(Field.ClientExecuteTime);
        Request<ModifyPrivateLinkServerRequest> request = null;
        Response<ModifyPrivateLinkServerResult> response = null;
        try {
            kscRequestMetrics.startEvent(Field.RequestMarshallTime);
            try {
                request = new ModifyPrivateLinkServerRequestMarshaller()
                        .marshall(modifyPrivateLinkServerRequest);
                request.setKscRequestMetrics(kscRequestMetrics);
            } finally {
                kscRequestMetrics.endEvent(Field.RequestMarshallTime);
            }
            StaxResponseHandler<ModifyPrivateLinkServerResult> responseHandler = new StaxResponseHandler<ModifyPrivateLinkServerResult>(
                    new ModifyPrivateLinkServerResultStaxUnmarshaller());
            response = invoke(request, responseHandler, executionContext);

            return response.getKscResponse();
        } finally {
            endClientExecution(kscRequestMetrics, request, response);
        }
    }

    @Override
    public AssociatePrivateLinkServerResult associatePrivateLinkServer(AssociatePrivateLinkServerRequest associatePrivateLinkServerRequest) {
        ExecutionContext executionContext = createExecutionContext(associatePrivateLinkServerRequest);
        KscRequestMetrics kscRequestMetrics = executionContext.getKscRequestMetrics();
        kscRequestMetrics.startEvent(Field.ClientExecuteTime);
        Request<AssociatePrivateLinkServerRequest> request = null;
        Response<AssociatePrivateLinkServerResult> response = null;
        try {
            kscRequestMetrics.startEvent(Field.RequestMarshallTime);
            try {
                request = new AssociatePrivateLinkServerRequestMarshaller()
                        .marshall(associatePrivateLinkServerRequest);
                request.setKscRequestMetrics(kscRequestMetrics);
            } finally {
                kscRequestMetrics.endEvent(Field.RequestMarshallTime);
            }
            StaxResponseHandler<AssociatePrivateLinkServerResult> responseHandler = new StaxResponseHandler<AssociatePrivateLinkServerResult>(
                    new AssociatePrivateLinkServerResultStaxUnmarshaller());
            response = invoke(request, responseHandler, executionContext);

            return response.getKscResponse();
        } finally {
            endClientExecution(kscRequestMetrics, request, response);
        }
    }

    @Override
    public DeletePrivateLinkResult deletePrivateLink(DeletePrivateLinkRequest deletePrivateLinkRequest) {
        ExecutionContext executionContext = createExecutionContext(deletePrivateLinkRequest);
        KscRequestMetrics kscRequestMetrics = executionContext.getKscRequestMetrics();
        kscRequestMetrics.startEvent(Field.ClientExecuteTime);
        Request<DeletePrivateLinkRequest> request = null;
        Response<DeletePrivateLinkResult> response = null;
        try {
            kscRequestMetrics.startEvent(Field.RequestMarshallTime);
            try {
                request = new DeletePrivateLinkRequestMarshaller()
                        .marshall(deletePrivateLinkRequest);
                request.setKscRequestMetrics(kscRequestMetrics);
            } finally {
                kscRequestMetrics.endEvent(Field.RequestMarshallTime);
            }
            StaxResponseHandler<DeletePrivateLinkResult> responseHandler = new StaxResponseHandler<DeletePrivateLinkResult>(
                    new DeletePrivateLinkResultStaxUnmarshaller());
            response = invoke(request, responseHandler, executionContext);

            return response.getKscResponse();
        } finally {
            endClientExecution(kscRequestMetrics, request, response);
        }
    }

    @Override
    public DescribePrivateLinkResult describePrivateLink(DescribePrivateLinkRequest describePrivateLinkRequest) {
        ExecutionContext executionContext = createExecutionContext(describePrivateLinkRequest);
        KscRequestMetrics kscRequestMetrics = executionContext.getKscRequestMetrics();
        kscRequestMetrics.startEvent(Field.ClientExecuteTime);
        Request<DescribePrivateLinkRequest> request = null;
        Response<DescribePrivateLinkResult> response = null;
        try {
            kscRequestMetrics.startEvent(Field.RequestMarshallTime);
            try {
                request = new DescribePrivateLinkRequestMarshaller()
                        .marshall(describePrivateLinkRequest);
                request.setKscRequestMetrics(kscRequestMetrics);
            } finally {
                kscRequestMetrics.endEvent(Field.RequestMarshallTime);
            }
            StaxResponseHandler<DescribePrivateLinkResult> responseHandler = new StaxResponseHandler<DescribePrivateLinkResult>(
                    new DescribePrivateLinkResultStaxUnmarshaller());
            response = invoke(request, responseHandler, executionContext);

            return response.getKscResponse();
        } finally {
            endClientExecution(kscRequestMetrics, request, response);
        }
    }

    @Override
    public ListPrivateLinkServerResult listPrivateLinkServer(ListPrivateLinkServerRequest listPrivateLinkServerRequest) {
        ExecutionContext executionContext = createExecutionContext(listPrivateLinkServerRequest);
        KscRequestMetrics kscRequestMetrics = executionContext.getKscRequestMetrics();
        kscRequestMetrics.startEvent(Field.ClientExecuteTime);
        Request<ListPrivateLinkServerRequest> request = null;
        Response<ListPrivateLinkServerResult> response = null;
        try {
            kscRequestMetrics.startEvent(Field.RequestMarshallTime);
            try {
                request = new ListPrivateLinkServerRequestMarshaller()
                        .marshall(listPrivateLinkServerRequest);
                request.setKscRequestMetrics(kscRequestMetrics);
            } finally {
                kscRequestMetrics.endEvent(Field.RequestMarshallTime);
            }
            StaxResponseHandler<ListPrivateLinkServerResult> responseHandler = new StaxResponseHandler<ListPrivateLinkServerResult>(
                    new ListPrivateLinkServerResultStaxUnmarshaller());
            response = invoke(request, responseHandler, executionContext);

            return response.getKscResponse();
        } finally {
            endClientExecution(kscRequestMetrics, request, response);
        }
    }

    @Override
    public RemovePrivateLinkResult removePrivateLink(RemovePrivateLinkRequest removePrivateLinkRequest) {
        ExecutionContext executionContext = createExecutionContext(removePrivateLinkRequest);
        KscRequestMetrics kscRequestMetrics = executionContext.getKscRequestMetrics();
        kscRequestMetrics.startEvent(Field.ClientExecuteTime);
        Request<RemovePrivateLinkRequest> request = null;
        Response<RemovePrivateLinkResult> response = null;
        try {
            kscRequestMetrics.startEvent(Field.RequestMarshallTime);
            try {
                request = new RemovePrivateLinkRequestMarshaller()
                        .marshall(removePrivateLinkRequest);
                request.setKscRequestMetrics(kscRequestMetrics);
            } finally {
                kscRequestMetrics.endEvent(Field.RequestMarshallTime);
            }
            StaxResponseHandler<RemovePrivateLinkResult> responseHandler = new StaxResponseHandler<RemovePrivateLinkResult>(
                    new RemovePrivateLinkResultStaxUnmarshaller());
            response = invoke(request, responseHandler, executionContext);

            return response.getKscResponse();
        } finally {
            endClientExecution(kscRequestMetrics, request, response);
        }
    }

    @Override
    public AcceptPrivateLinkResult acceptPrivateLink(AcceptPrivateLinkRequest acceptPrivateLinkRequest) {
        ExecutionContext executionContext = createExecutionContext(acceptPrivateLinkRequest);
        KscRequestMetrics kscRequestMetrics = executionContext.getKscRequestMetrics();
        kscRequestMetrics.startEvent(Field.ClientExecuteTime);
        Request<AcceptPrivateLinkRequest> request = null;
        Response<AcceptPrivateLinkResult> response = null;
        try {
            kscRequestMetrics.startEvent(Field.RequestMarshallTime);
            try {
                request = new AcceptPrivateLinkRequestMarshaller()
                        .marshall(acceptPrivateLinkRequest);
                request.setKscRequestMetrics(kscRequestMetrics);
            } finally {
                kscRequestMetrics.endEvent(Field.RequestMarshallTime);
            }
            StaxResponseHandler<AcceptPrivateLinkResult> responseHandler = new StaxResponseHandler<AcceptPrivateLinkResult>(
                    new AcceptPrivateLinkResultStaxUnmarshaller());
            response = invoke(request, responseHandler, executionContext);

            return response.getKscResponse();
        } finally {
            endClientExecution(kscRequestMetrics, request, response);
        }
    }

    @Override
    public RejectPrivateLinkResult rejectPrivateLink(RejectPrivateLinkRequest rejectPrivateLinkRequest) {
        ExecutionContext executionContext = createExecutionContext(rejectPrivateLinkRequest);
        KscRequestMetrics kscRequestMetrics = executionContext.getKscRequestMetrics();
        kscRequestMetrics.startEvent(Field.ClientExecuteTime);
        Request<RejectPrivateLinkRequest> request = null;
        Response<RejectPrivateLinkResult> response = null;
        try {
            kscRequestMetrics.startEvent(Field.RequestMarshallTime);
            try {
                request = new RejectPrivateLinkRequestMarshaller()
                        .marshall(rejectPrivateLinkRequest);
                request.setKscRequestMetrics(kscRequestMetrics);
            } finally {
                kscRequestMetrics.endEvent(Field.RequestMarshallTime);
            }
            StaxResponseHandler<RejectPrivateLinkResult> responseHandler = new StaxResponseHandler<RejectPrivateLinkResult>(
                    new RejectPrivateLinkResultStaxUnmarshaller());
            response = invoke(request, responseHandler, executionContext);

            return response.getKscResponse();
        } finally {
            endClientExecution(kscRequestMetrics, request, response);
        }
    }


    private void init() {
		exceptionUnmarshallers.add(new StandardErrorUnmarshaller());
		exceptionUnmarshallers.add(new LegacyErrorUnmarshaller());
		setServiceNameIntern(DEFAULT_SIGNING_NAME);
		setEndpointPrefix(DEFAULT_ENDPOINT_PREFIX);
		// calling this.setEndPoint(...) will also modify the signer accordingly
		setEndpoint("http://slb.cn-beijing-6.api.ksyun.com");
	}

	/**
	 * Normal invoke with authentication. Credentials are required and may be
	 * overriden at the request level.
	 **/
	private <X, Y extends KscWebServiceRequest> Response<X> invoke(Request<Y> request,
			HttpResponseHandler<KscWebServiceResponse<X>> responseHandler, ExecutionContext executionContext) {

		executionContext.setCredentialsProvider(
				CredentialUtils.getCredentialsProvider(request.getOriginalRequest(), kscCredentialsProvider));

		return doInvoke(request, responseHandler, executionContext);
	}

	/**
	 * Invoke the request using the http client. Assumes credentials (or lack
	 * thereof) have been configured in the ExecutionContext beforehand.
	 **/
	private <X, Y extends KscWebServiceRequest> Response<X> doInvoke(Request<Y> request,
			HttpResponseHandler<KscWebServiceResponse<X>> responseHandler, ExecutionContext executionContext) {
		request.setEndpoint(endpoint);
		request.setTimeOffset(timeOffset);

		DefaultErrorResponseHandler errorResponseHandler = new DefaultErrorResponseHandler(exceptionUnmarshallers);

		return client.execute(request, responseHandler, errorResponseHandler, executionContext);
	}
}
