#ifndef PUNCT_H
#define PUNCT_H

#include<vector>

class Punct
{
private:
	std::vector<double> valuesVector;

public:
	Punct(std::vector<double> vals)
	{
		valuesVector = vals;
	}

	std::vector<double> get_vals() const
	{
		return valuesVector;
	}
};

#endif