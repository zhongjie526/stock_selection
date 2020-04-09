def implied_r(price, nav, eps,default_rate,return_volitility):
	return np.irr([nav-price]+simulate_returns(eps,default_rate,return_volitility))




def simulate_returns(eps,default_rate,return_volitility):
	returns = np.random.normal(eps,return_volitility,np.random.geometric(default_rate))
	avg_r = np.mean(returns)
	print ("default in year {} with average EPS of {}".format(str(len(returns)),str(avg_r)))
	return returns



def simulate_price(eps,default_rate,return_volitility,irr,nav):
	returns = simulate_returns(eps,default_rate,return_volitility)
	price_sim= nav+sum([r/(1+irr/100.0)**(year+1) for year,r in enumerate(returns)])
	print ("simulate price is {}".format(price_sim))
	return price_sim

def match_price(price,eps,default_rate,return_volitility,irr,nav):
	price_sim = simulate_price(eps,default_rate,return_volitility,irr,nav)
	n=0
	while(abs(price_sim/price-1)>0.01):
		print ("iteration {}".format(str(n+1)))
		price_sim = simulate_price(eps,default_rate,return_volitility,irr,nav)
		n+=1


